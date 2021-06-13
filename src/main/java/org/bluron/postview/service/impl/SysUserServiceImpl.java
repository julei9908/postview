package org.bluron.postview.service.impl;

import org.bluron.postview.common.constant.StateEnum;
import org.bluron.postview.common.entity.pojo.Page;
import org.bluron.postview.dao.SysUserDao;
import org.bluron.postview.entity.pojo.SysUser;
import org.bluron.postview.entity.vo.SysUserVo;
import org.bluron.postview.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户SERVICE实现
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl implements SysUserService {

    private static final Logger LOG = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 保存
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public void save(SysUser bean) {
        String encode = passwordEncoder.encode(bean.getPassword());
        bean.setPassword(encode);
        bean.setState(StateEnum.ENABLE.getState());
        sysUserDao.save(bean);
    }

    /**
     * 更新
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public void update(SysUser bean) {
        sysUserDao.update(bean);
    }

    /**
     * 更新不为空字段
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public void updateIfNotNull(SysUser bean) {
        sysUserDao.updateIfNotNull(bean);
    }

    /**
     * 根据主键查询
     *
     * @param userId
     * @return SysUser
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public SysUser get(Integer userId) {
        return sysUserDao.get(userId);
    }

    /**
     * 根据用户名查询
     *
     * @param username
     * @return SysUser
     * @author JuLei
     * @since 1.0.0_2019年11月16日
     */
    @Override
    public SysUser getByUsername(String username) {
        return sysUserDao.getByUsername(username);
    }

    /**
     * 查询
     *
     * @param sysUserVo
     * @return List<SysUser>
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public List<SysUser> list(SysUserVo sysUserVo) {
        return sysUserDao.pageQuery(sysUserVo);
    }

    /**
     * 分页查询
     *
     * @param sysUserVo
     * @return Page
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public Page pageQuery(SysUserVo sysUserVo) {
        Page page = null;
        try {
            List<SysUser> sysUsers = sysUserDao.pageQuery(sysUserVo);
            if (null != sysUsers) {
                page = new Page(sysUserVo.getTotal(), sysUsers);
            } else {
                page = new Page();
            }
        } catch (Exception e) {
            LOG.warn("查询用户出错", e);
        }
        return null == page ? new Page() : page;
    }

    /**
     * 修改密码
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @Override
    public void modifyPass(SysUser bean) {
        SysUser sysUser = sysUserDao.getByUsername(bean.getUsername());
        String encode = passwordEncoder.encode(bean.getPassword());
        SysUser newBean = new SysUser();
        newBean.setUserId(sysUser.getUserId());
        newBean.setPassword(encode);
        sysUserDao.updateIfNotNull(newBean);
    }

}
