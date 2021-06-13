package org.bluron.postview.entity.pojo;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bluron.postview.common.util.DateJsonDeserializer;
import org.bluron.postview.common.util.DateJsonSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色菜单POJO
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = -8990365266841530654L;

    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 菜单id
     */
    private Integer menuId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer value) {
        this.roleId = value;
    }

    public Integer getMenuId() {
        return this.menuId;
    }

    public void setMenuId(Integer value) {
        this.menuId = value;
    }

    @JsonSerialize(using = DateJsonSerializer.class)
    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    @JsonDeserialize(using = DateJsonDeserializer.class)
    public void setCreateTime(LocalDateTime value) {
        this.createTime = value;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
