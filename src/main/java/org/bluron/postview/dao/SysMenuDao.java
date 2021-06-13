package org.bluron.postview.dao;

import org.bluron.postview.entity.pojo.SysMenu;
import org.bluron.postview.entity.vo.SysMenuVo;

import java.util.List;

/**
 * 菜单DAO
 *
 * @author JuLei
 * @since 1.0.0_2019年10月7日
 */
public interface SysMenuDao {

    /**
     * 保存
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    void save(SysMenu bean);

    /**
     * 更新
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    void update(SysMenu bean);

    /**
     * 更新不为空字段
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    void updateIfNotNull(SysMenu bean);

    /**
     * 根据主键查询
     *
     * @param menuId
     * @return SysMenu
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    SysMenu get(Integer menuId);

    /**
     * 分页查询
     *
     * @param sysMenuVo
     * @return List<SysMenu>
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    List<SysMenu> pageQuery(SysMenuVo sysMenuVo);

    /**
     * 导航菜单查询
     *
     * @param roleId
     * @return List<SysMenu>
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    List<SysMenu> navMenu(Integer roleId);

}
