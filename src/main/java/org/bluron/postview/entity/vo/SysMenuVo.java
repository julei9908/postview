package org.bluron.postview.entity.vo;

import com.alibaba.fastjson.JSON;
import org.bluron.postview.common.entity.vo.PageQueryParams;

import java.io.Serializable;

/**
 * 菜单VO
 *
 * @author JuLei
 * @since 1.0.0_2019年10月7日
 */
public class SysMenuVo extends PageQueryParams implements Serializable {

    private static final long serialVersionUID = -2171377853714708829L;

    /**
     * 菜单id
     */
    private Integer menuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 上级菜单id
     */
    private Integer supMenuId;
    /**
     * 状态 1-启用 0-禁用
     */
    private Integer state;
    /**
     * 排序
     */
    private Integer order;
    /**
     * 查询的菜单层级
     */
    private Integer level;

    public Integer getMenuId() {
        return this.menuId;
    }

    public void setMenuId(Integer value) {
        this.menuId = value;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuName(String value) {
        this.menuName = value;
    }

    public Integer getSupMenuId() {
        return this.supMenuId;
    }

    public void setSupMenuId(Integer value) {
        this.supMenuId = value;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer value) {
        this.state = value;
    }

    public Integer getOrder() {
        return this.order;
    }

    public void setOrder(Integer value) {
        this.order = value;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
