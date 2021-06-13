package org.bluron.postview.service.impl;

import org.bluron.postview.common.entity.pojo.Page;
import org.bluron.postview.dao.SysUserRoleDao;
import org.bluron.postview.entity.pojo.SysUserRole;
import org.bluron.postview.entity.vo.SysUserRoleVo;
import org.bluron.postview.service.SysUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色SERVICE实现
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserRoleServiceImpl implements SysUserRoleService {

    private static final Logger LOG = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);

    @Resource
    private SysUserRoleDao sysUserRoleDao;

    /**
     * 保存
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public void save(SysUserRole bean) {
        sysUserRoleDao.save(bean);
    }

    /**
     * 更新
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public void update(SysUserRole bean) {
        sysUserRoleDao.update(bean);
    }

    /**
     * 根据用户ID获取角色
     *
     * @param userId
     * @return SysUserRole
     * @author JuLei
     * @since 1.0.0_2019年11月16日
     */
    @Override
    public SysUserRole getRoleByUserId(Integer userId) {
        return sysUserRoleDao.getRoleByUserId(userId);
    }

    /**
     * 查询
     *
     * @param sysUserRoleVo
     * @return List<SysUserRole>
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public List<SysUserRole> list(SysUserRoleVo sysUserRoleVo) {
        return sysUserRoleDao.pageQuery(sysUserRoleVo);
    }

    /**
     * 分页查询
     *
     * @param sysUserRoleVo
     * @return Page
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public Page pageQuery(SysUserRoleVo sysUserRoleVo) {
        Page page = null;
        try {
            List<SysUserRole> sysUserRoles = sysUserRoleDao.pageQuery(sysUserRoleVo);
            if (null != sysUserRoles) {
                page = new Page(sysUserRoleVo.getTotal(), sysUserRoles);
            } else {
                page = new Page();
            }
        } catch (Exception e) {
            LOG.warn("查询SysUserRole出错", e);
        }
        return null == page ? new Page() : page;
    }

}
