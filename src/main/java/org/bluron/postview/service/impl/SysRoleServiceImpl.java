package org.bluron.postview.service.impl;

import org.bluron.postview.common.constant.StateEnum;
import org.bluron.postview.common.entity.pojo.Page;
import org.bluron.postview.dao.SysRoleDao;
import org.bluron.postview.entity.pojo.SysRole;
import org.bluron.postview.entity.vo.SysRoleVo;
import org.bluron.postview.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色SERVICE实现
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl implements SysRoleService {

    private static final Logger LOG = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Resource
    private SysRoleDao sysRoleDao;

    /**
     * 保存
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public void save(SysRole bean) {
        bean.setState(StateEnum.ENABLE.getState());
        sysRoleDao.save(bean);
    }

    /**
     * 更新
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public void update(SysRole bean) {
        sysRoleDao.update(bean);
    }

    /**
     * 更新不为空字段
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public void updateIfNotNull(SysRole bean) {
        sysRoleDao.updateIfNotNull(bean);
    }

    /**
     * 根据主键查询
     *
     * @param roleId
     * @return SysRole
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public SysRole get(Integer roleId) {
        return sysRoleDao.get(roleId);
    }

    /**
     * 查询
     *
     * @param sysRoleVo
     * @return List<SysRole>
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public List<SysRole> list(SysRoleVo sysRoleVo) {
        return sysRoleDao.pageQuery(sysRoleVo);
    }

    /**
     * 分页查询
     *
     * @param sysRoleVo
     * @return Page
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public Page pageQuery(SysRoleVo sysRoleVo) {
        Page page = null;
        try {
            List<SysRole> sysRoles = sysRoleDao.pageQuery(sysRoleVo);
            if (null != sysRoles) {
                page = new Page(sysRoleVo.getTotal(), sysRoles);
            } else {
                page = new Page();
            }
        } catch (Exception e) {
            LOG.warn("查询SysRole出错", e);
        }
        return null == page ? new Page() : page;
    }

}
