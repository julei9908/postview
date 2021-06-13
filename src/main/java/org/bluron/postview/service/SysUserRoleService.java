package org.bluron.postview.service;

import org.bluron.postview.common.entity.pojo.Page;
import org.bluron.postview.entity.pojo.SysUserRole;
import org.bluron.postview.entity.vo.SysUserRoleVo;

import java.util.List;

/**
 * 用户角色SERVICE
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
public interface SysUserRoleService {

    /**
     * 保存
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    void save(SysUserRole bean);

    /**
     * 更新
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    void update(SysUserRole bean);

    /**
     * 根据用户ID获取角色
     *
     * @param userId
     * @return SysUserRole
     * @author JuLei
     * @since 1.0.0_2019年11月16日
     */
    SysUserRole getRoleByUserId(Integer userId);

    /**
     * 查询
     *
     * @param sysUserRoleVo
     * @return List<SysUserRole>
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    List<SysUserRole> list(SysUserRoleVo sysUserRoleVo);

    /**
     * 分页查询
     *
     * @param sysUserRoleVo
     * @return Page
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    Page pageQuery(SysUserRoleVo sysUserRoleVo);

}
