package org.bluron.postview.common.interceptor;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.RowBounds;
import org.bluron.postview.common.entity.pojo.Page;
import org.bluron.postview.common.entity.vo.PageQueryParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 拦截StatementHandler的prepare方法，重写sql语句实现物理分页
 *
 * @author JuLei
 * @since 1.0.0_2019年09月21日
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
@Component
public class PageInterceptor implements Interceptor {

    private static final Logger LOG = LoggerFactory.getLogger(PageInterceptor.class);

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();

    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

    private static final DefaultReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

    private static final String DEFAULT_PAGE_SQL_ID = ".*([Pp])ageQuery$"; // 需要拦截的ID(正则匹配)

    /**
     * mybatis拦截器重写分页sql
     *
     * @param invocation
     * @return Object
     * @author JuLei
     * @since 1.0.0_2019年09月21日
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,
                DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
        // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
        while (metaStatementHandler.hasGetter("h")) {
            Object object = metaStatementHandler.getValue("h");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,
                    DEFAULT_REFLECTOR_FACTORY);
        }
        // 分离最后一个代理对象的目标类
        while (metaStatementHandler.hasGetter("target")) {
            Object object = metaStatementHandler.getValue("target");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,
                    DEFAULT_REFLECTOR_FACTORY);
        }
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
        // 只重写需要分页的sql语句。通过MappedStatement的ID匹配，默认重写以Page结尾的MappedStatement的sql
        if (mappedStatement.getId().matches(DEFAULT_PAGE_SQL_ID)
                || mappedStatement.getId().matches(PageConstant.PAGE_SQL_ID_REGEX)) {
            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
            Object parameterObject = boundSql.getParameterObject();
            if (parameterObject == null) {
                throw new NullPointerException("parameterObject is null!");
            } else {
                PageQueryParams page = (PageQueryParams) metaStatementHandler
                        .getValue("delegate.boundSql.parameterObject");
                String sql = boundSql.getSql();
                // 计算总记录数
                page.setTotal(getTotalCount(sql, (Connection) invocation.getArgs()[0], mappedStatement, boundSql));
                // 重写sql
                String pageSql = buildPageSql(sql, page, page.getTotal());
                metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
                // 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
                metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
                metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
            }
        }
        // 将执行权交给下一个拦截器
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
    }

    /**
     * 从数据库里查询总的记录数并计算总页数，回写进分页参数PageParameter
     *
     * @param sql
     * @param connection
     * @param mappedStatement
     * @param boundSql
     * @return long
     * @author JuLei
     * @since 1.0.0_2019年09月21日
     */
    private long getTotalCount(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql) {
        // 记录总记录数
        long totalCount = 0;
        String countSql = "select count(0) from (" + sql + ") total";
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
            countStmt = connection.prepareStatement(countSql);
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,
                    boundSql.getParameterMappings(), boundSql.getParameterObject());
            setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
            rs = countStmt.executeQuery();
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.warn("分页查询总记录数出现异常", e);
        } finally {
            try {
                if (null != rs) {
                    rs.close();
                }
            } catch (SQLException e) {
                LOG.warn("分页查询总记录数出现异常", e);
            }
            try {
                if (null != countStmt) {
                    countStmt.close();
                }
            } catch (SQLException e) {
                LOG.warn("分页查询总记录数出现异常", e);
            }
        }
        return totalCount;
    }

    /**
     * 对SQL参数(?)设值
     *
     * @param ps
     * @param mappedStatement
     * @param boundSql
     * @param parameterObject
     * @author JuLei
     * @since 1.0.0_2019年09月21日
     */
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
                               Object parameterObject) throws SQLException {
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        parameterHandler.setParameters(ps);
    }

    /**
     * 根据数据库类型，生成特定的分页sql
     *
     * @param sql
     * @param page
     * @param totalCount
     * @return String
     * @author JuLei
     * @since 1.0.0_2019年09月21日
     */
    private String buildPageSql(String sql, PageQueryParams page, long totalCount) {
        if (page != null) {
            String pageSql;
            if ("mysql".equalsIgnoreCase(PageConstant.DB_NAME)) {
                pageSql = buildPageSqlForMysql(sql, page, totalCount);
            } else if ("oracle".equalsIgnoreCase(PageConstant.DB_NAME)) {
                pageSql = buildPageSqlForOracle(sql, page, totalCount);
            } else {
                pageSql = sql;
            }
            return pageSql;
        } else {
            return sql;
        }
    }

    /**
     * mysql的分页语句
     *
     * @param sql
     * @param page
     * @param totalCount
     * @return StringBuilder
     * @author JuLei
     * @since 1.0.0_2019年09月21日
     */
    private String buildPageSqlForMysql(String sql, PageQueryParams page, long totalCount) {
        StringBuilder pageSql = new StringBuilder(100);
        String beginRow = String.valueOf(Page.getStartOfPage(page.getPageNo(), page.getPageSize()));
        pageSql.append(sql);
        pageSql.append(" limit ").append(beginRow).append(",").append(page.getPageSize());
        return pageSql.toString();
    }

    /**
     * oracle的分页语句
     *
     * @param sql
     * @param page
     * @param totalCount
     * @return StringBuilder
     * @author JuLei
     * @since 1.0.0_2019年09月21日
     */
    private String buildPageSqlForOracle(String sql, PageQueryParams page, long totalCount) {
        StringBuilder pageSql = new StringBuilder(100);
        long start = Page.getStartOfPage(totalCount, page.getPageNo(), page.getPageSize());
        String beginRow = String.valueOf(start);
        String endRow = String.valueOf(start + page.getPageSize());
        pageSql.append("select * from ( select temp.*, rownum row_id from ( ");
        pageSql.append(sql);
        pageSql.append(") temp where rownum <= ").append(endRow);
        pageSql.append(") where row_id > ").append(beginRow);
        return pageSql.toString();
    }

}
