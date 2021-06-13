package org.bluron.postview.dao;

import org.bluron.postview.entity.pojo.SysRoleMenu;
import org.bluron.postview.entity.vo.SysRoleMenuVo;

import java.util.List;

/**
 * 角色菜单DAO
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
public interface SysRoleMenuDao {

    /**
     * 保存
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    void save(SysRoleMenu bean);

    /**
     * 删除
     *
     * @param roleId
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    void delete(Integer roleId);

    /**
     * 根据角色Id查询
     *
     * @param roleId
     * @return List<SysRoleMenu>
     * @author JuLei
     * @since 1.0.0_2019年11月16日
     */
    List<SysRoleMenu> getMenuByRoleId(Integer roleId);

    /**
     * 分页查询
     *
     * @param sysRoleMenuVo
     * @return List<SysRoleMenu>
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    List<SysRoleMenu> pageQuery(SysRoleMenuVo sysRoleMenuVo);

}
