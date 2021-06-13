package org.bluron.postview.entity.vo;

import com.alibaba.fastjson.JSON;
import org.bluron.postview.common.entity.vo.PageQueryParams;

import java.io.Serializable;

/**
 * 角色菜单VO
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
public class SysRoleMenuVo extends PageQueryParams implements Serializable {

    private static final long serialVersionUID = -1703160705210551967L;

    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 菜单id
     */
    private Integer menuId;

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

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
