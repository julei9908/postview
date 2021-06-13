package org.bluron.postview.service.impl;

import org.bluron.postview.common.entity.pojo.Page;
import org.bluron.postview.dao.SysMenuDao;
import org.bluron.postview.entity.pojo.SysMenu;
import org.bluron.postview.entity.vo.SysMenuVo;
import org.bluron.postview.service.SysMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单SERVICE实现
 *
 * @author JuLei
 * @since 1.0.0_2019年10月7日
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysMenuServiceImpl implements SysMenuService {

    private static final Logger LOG = LoggerFactory.getLogger(SysMenuServiceImpl.class);

    @Resource
    private SysMenuDao sysMenuDao;

    /**
     * 保存
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @Override
    public void save(SysMenu bean) {
        sysMenuDao.save(bean);
    }

    /**
     * 更新
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @Override
    public void update(SysMenu bean) {
        sysMenuDao.update(bean);
    }

    /**
     * 更新不为空字段
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @Override
    public void updateIfNotNull(SysMenu bean) {
        sysMenuDao.updateIfNotNull(bean);
    }

    /**
     * 根据主键查询
     *
     * @param menuId
     * @return SysMenu
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @Override
    public SysMenu get(Integer menuId) {
        return sysMenuDao.get(menuId);
    }

    /**
     * 查询
     *
     * @param sysMenuVo
     * @return List<SysMenu>
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @Override
    public List<SysMenu> list(SysMenuVo sysMenuVo) {
        return sysMenuDao.pageQuery(sysMenuVo);
    }

    /**
     * 导航菜单查询
     *
     * @param roleId
     * @return List<SysMenu>
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @Override
    public List<SysMenu> navMenu(Integer roleId) {
        return sysMenuDao.navMenu(roleId);
    }

    /**
     * 分页查询
     *
     * @param sysMenuVo
     * @return Page
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @Override
    public Page pageQuery(SysMenuVo sysMenuVo) {
        Page page = null;
        try {
            List<SysMenu> sysMenus = sysMenuDao.pageQuery(sysMenuVo);
            for (SysMenu sysMenu : sysMenus) {
                if (sysMenu.getSupMenuId() != null) {
                    SysMenu bean = sysMenuDao.get(sysMenu.getSupMenuId());
                    sysMenu.setSubMenuName(bean.getMenuName());
                }
            }
            if (!sysMenus.isEmpty()) {
                page = new Page(sysMenuVo.getTotal(), sysMenus);
            } else {
                page = new Page();
            }
        } catch (Exception e) {
            LOG.warn("查询菜单异常", e);
        }
        return null == page ? new Page() : page;
    }

}
