package org.bluron.postview.service;

import org.bluron.postview.common.entity.pojo.Page;
import org.bluron.postview.entity.pojo.SysRole;
import org.bluron.postview.entity.vo.SysRoleVo;

import java.util.List;

/**
 * 角色SERVICE
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
public interface SysRoleService {

    /**
     * 保存
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    void save(SysRole bean);

    /**
     * 更新
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    void update(SysRole bean);

    /**
     * 更新不为空字段
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    void updateIfNotNull(SysRole bean);

    /**
     * 根据主键查询
     *
     * @param roleId
     * @return SysRole
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    SysRole get(Integer roleId);

    /**
     * 查询
     *
     * @param sysRoleVo
     * @return List<SysRole>
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    List<SysRole> list(SysRoleVo sysRoleVo);

    /**
     * 分页查询
     *
     * @param sysRoleVo
     * @return Page
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    Page pageQuery(SysRoleVo sysRoleVo);

}
