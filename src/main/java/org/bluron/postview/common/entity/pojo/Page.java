package org.bluron.postview.common.entity.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页信息实体
 *
 * @author JuLei
 * @since 1.0.0_2019年09月21日
 */
public class Page implements Serializable {

    private static final long serialVersionUID = -2557167454285671436L;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 当前页数据,类型为List
     */
    private Object rows;

    /**
     * 分页列表构造方法(空页)
     *
     * @author JuLei
     * @since 1.0.0_2019年09月21日
     */
    public Page() {
        this(0, new ArrayList());
    }

    /**
     * 分页列表构造方法(不分页)
     *
     * @param rows
     * @author JuLei
     * @since 1.0.0_2019年09月21日
     */
    public Page(Object rows) {
        this.rows = rows;
        if (rows instanceof List) {
            this.total = ((List) rows).size();
        } else if (null != rows) {
            this.total = 1;
        }
    }

    /**
     * 分页列表构造方法(含列表)
     *
     * @param total
     * @param rows
     * @author JuLei
     * @since 1.0.0_2019年09月21日
     */
    public Page(long total, Object rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    /**
     * 获取任一页第一条数据在数据集的位置（不考虑总记录数）
     *
     * @param pageNo   从1开始的页号
     * @param pageSize 每页记录条数
     * @return long
     * @author JuLei
     * @since 1.0.0_2019年09月21日
     */
    public static long getStartOfPage(int pageNo, int pageSize) {
        long start = (pageNo - 1) * pageSize;
        return start < 0 ? 0 : start;
    }

    /**
     * 获取任一页第一条数据在数据集的位置(考虑总记录数并做调整)
     *
     * @param total    总记录数
     * @param pageNo   从1开始的页号
     * @param pageSize 每页记录条数
     * @return long
     * @author JuLei
     * @since 1.0.0_2019年09月21日
     */
    public static long getStartOfPage(long total, int pageNo, int pageSize) {
        long start = (pageNo - 1) * pageSize;
        // 如果超过了最后一行，则自动退回到最后一页的第一行
        if (start >= total) {
            start = Page.getStartOfPage(pageNo - 1, pageSize);
        }
        return start < 0 ? 0 : start;
    }

}
