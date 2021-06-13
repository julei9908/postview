package org.bluron.postview.common.entity.vo;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 分页查询的通用参数
 *
 * @author JuLei
 * @since 1.0.0_2019年09月21日
 */
public class PageQueryParams implements Serializable {

    private static final long serialVersionUID = 7697206826203859993L;

    /**
     * 分页查询的页号(前台视图页面传入)
     */
    private int pageNo = 1;

    /**
     * 分页查询数据时，每页显示的记录数(前台视图页面传入)
     */
    private int pageSize = 10;

    /**
     * 总记录数（由系统查询数据库后设置，无需过问）
     */
    private long total;

    /**
     * 本次查询记录的开始位置（由系统查询数据库计数后设置，无需过问）
     */
    private long start;

    /**
     * 通用查询参数
     */
    private String query;

    public PageQueryParams() {
    }

    public PageQueryParams(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
