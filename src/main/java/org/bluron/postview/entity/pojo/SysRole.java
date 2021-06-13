package org.bluron.postview.entity.pojo;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bluron.postview.common.util.DateJsonDeserializer;
import org.bluron.postview.common.util.DateJsonSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色POJO
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
public class SysRole implements Serializable {

    private static final long serialVersionUID = 4424733493835395703L;

    /**
     * 用户id
     */
    private Integer roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 状态 1-启用 0-禁用
     */
    private Integer state;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer value) {
        this.roleId = value;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String value) {
        this.roleName = value;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer value) {
        this.state = value;
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

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
