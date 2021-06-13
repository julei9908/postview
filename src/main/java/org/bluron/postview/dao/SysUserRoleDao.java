package org.bluron.postview.dao;

import org.bluron.postview.entity.pojo.SysUserRole;
import org.bluron.postview.entity.vo.SysUserRoleVo;

import java.util.List;

/**
 * 用户角色DAO
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
public interface SysUserRoleDao {

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
     * 根据用户ID查询
     *
     * @param userId
     * @return SysUserRole
     * @author JuLei
     * @since 1.0.0_2019年11月16日
     */
    SysUserRole getRoleByUserId(Integer userId);

    /**
     * 分页查询
     *
     * @param sysUserRoleVo
     * @return List<SysUserRole>
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    List<SysUserRole> pageQuery(SysUserRoleVo sysUserRoleVo);

}
