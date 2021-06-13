package org.bluron.postview.entity.pojo;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bluron.postview.common.util.DateJsonDeserializer;
import org.bluron.postview.common.util.DateJsonSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜单POJO
 *
 * @author JuLei
 * @since 1.0.0_2019年10月7日
 */
public class SysMenu implements Serializable {

    private static final long serialVersionUID = -7187459416405980390L;

    /**
     * 菜单id
     */
    private Integer menuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单地址
     */
    private String menuUrl;
    /**
     * 菜单图标
     */
    private String menuIcon;
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
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 上级菜单名称
     */
    private String subMenuName;

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

    public String getMenuUrl() {
        return this.menuUrl;
    }

    public void setMenuUrl(String value) {
        this.menuUrl = value;
    }

    public String getMenuIcon() {
        return this.menuIcon;
    }

    public void setMenuIcon(String value) {
        this.menuIcon = value;
    }

    public Integer getSupMenuId() {
        return supMenuId;
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

    @JsonSerialize(using = DateJsonSerializer.class)
    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    @JsonDeserialize(using = DateJsonDeserializer.class)
    public void setCreateTime(LocalDateTime value) {
        this.createTime = value;
    }

    @JsonSerialize(using = DateJsonSerializer.class)
    public LocalDateTime getUpdateTime() {
        return this.updateTime;
    }

    @JsonDeserialize(using = DateJsonDeserializer.class)
    public void setUpdateTime(LocalDateTime value) {
        this.updateTime = value;
    }

    public String getSubMenuName() {
        return subMenuName;
    }

    public void setSubMenuName(String subMenuName) {
        this.subMenuName = subMenuName;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
