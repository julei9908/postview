package org.bluron.postview.entity.pojo;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bluron.postview.common.util.DateJsonDeserializer;
import org.bluron.postview.common.util.DateJsonSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户角色POJO
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = -76432970063749568L;

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer value) {
        this.userId = value;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer value) {
        this.roleId = value;
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
