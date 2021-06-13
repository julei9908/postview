package org.bluron.postview.common.interceptor;

/**
 * 分页常量
 *
 * @author JuLei
 * @since 1.0.0_2019年9月21日
 */
public class PageConstant {

    /**
     * 默认数据库
     */
    public static final String DB_NAME = "mysql";

    /**
     * 要拦截并进行分页处理mybatis的sql的id(用于分页插件)
     */
    public static final String PAGE_SQL_ID_REGEX = ".*(P|p)ageQuery$";

}
