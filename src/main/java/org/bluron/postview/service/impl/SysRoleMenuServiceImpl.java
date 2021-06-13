package org.bluron.postview.service.impl;

import org.bluron.postview.common.entity.pojo.Page;
import org.bluron.postview.dao.SysRoleMenuDao;
import org.bluron.postview.entity.pojo.SysRoleMenu;
import org.bluron.postview.entity.vo.SysRoleMenuVo;
import org.bluron.postview.service.SysRoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色菜单SERVICE实现
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    private static final Logger LOG = LoggerFactory.getLogger(SysRoleMenuServiceImpl.class);

    @Resource
    private SysRoleMenuDao sysRoleMenuDao;

    /**
     * 保存
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public void save(SysRoleMenu bean) {
        sysRoleMenuDao.save(bean);
    }

    /**
     * 删除
     *
     * @param roleId
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public void delete(Integer roleId) {
        sysRoleMenuDao.delete(roleId);
    }

    /**
     * 根据角色Id查询
     *
     * @param roleId
     * @return List<SysRoleMenu>
     * @author JuLei
     * @since 1.0.0_2019年11月16日
     */
    @Override
    public List<SysRoleMenu> getMenuByRoleId(Integer roleId) {
        return sysRoleMenuDao.getMenuByRoleId(roleId);
    }

    /**
     * 查询
     *
     * @param sysRoleMenuVo
     * @return List<SysRoleMenu>
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public List<SysRoleMenu> list(SysRoleMenuVo sysRoleMenuVo) {
        return sysRoleMenuDao.pageQuery(sysRoleMenuVo);
    }

    /**
     * 分页查询
     *
     * @param sysRoleMenuVo
     * @return Page
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public Page pageQuery(SysRoleMenuVo sysRoleMenuVo) {
        Page page = null;
        try {
            List<SysRoleMenu> sysRoleMenus = sysRoleMenuDao.pageQuery(sysRoleMenuVo);
            if (null != sysRoleMenus) {
                page = new Page(sysRoleMenuVo.getTotal(), sysRoleMenus);
            } else {
                page = new Page();
            }
        } catch (Exception e) {
            LOG.warn("查询SysRoleMenu出错", e);
        }
        return null == page ? new Page() : page;
    }

}
