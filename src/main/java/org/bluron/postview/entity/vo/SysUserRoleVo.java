package org.bluron.postview.entity.vo;

import com.alibaba.fastjson.JSON;
import org.bluron.postview.common.entity.vo.PageQueryParams;

import java.io.Serializable;

/**
 * 用户角色VO
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
public class SysUserRoleVo extends PageQueryParams implements Serializable {

    private static final long serialVersionUID = -7487575133819680300L;

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 角色id
     */
    private Integer roleId;

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

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
