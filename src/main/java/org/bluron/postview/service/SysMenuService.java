package org.bluron.postview.service;

import org.bluron.postview.common.entity.pojo.Page;
import org.bluron.postview.entity.pojo.SysMenu;
import org.bluron.postview.entity.vo.SysMenuVo;

import java.util.List;

/**
 * 菜单SERVICE
 *
 * @author JuLei
 * @since 1.0.0_2019年10月7日
 */
public interface SysMenuService {

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
     * 查询
     *
     * @param sysMenuVo
     * @return List<SysMenu>
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    List<SysMenu> list(SysMenuVo sysMenuVo);

    /**
     * 导航菜单查询
     *
     * @param roleId
     * @return List<SysMenu>
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    List<SysMenu> navMenu(Integer roleId);

    /**
     * 分页查询
     *
     * @param sysMenuVo
     * @return Page
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    Page pageQuery(SysMenuVo sysMenuVo);

}
