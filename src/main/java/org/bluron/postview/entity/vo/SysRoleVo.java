package org.bluron.postview.entity.vo;

import com.alibaba.fastjson.JSON;
import org.bluron.postview.common.entity.vo.PageQueryParams;

import java.io.Serializable;

/**
 * 角色VO
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
public class SysRoleVo extends PageQueryParams implements Serializable {

    private static final long serialVersionUID = 8325609959310425063L;

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

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
