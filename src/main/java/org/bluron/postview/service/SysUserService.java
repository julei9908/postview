package org.bluron.postview.service;

import org.bluron.postview.common.entity.pojo.Page;
import org.bluron.postview.entity.pojo.SysUser;
import org.bluron.postview.entity.vo.SysUserVo;

import java.util.List;

/**
 * 用户SERVICE
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
public interface SysUserService {

    /**
     * 保存
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    void save(SysUser bean);

    /**
     * 更新
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    void update(SysUser bean);

    /**
     * 更新不为空字段
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    void updateIfNotNull(SysUser bean);

    /**
     * 根据主键查询
     *
     * @param userId
     * @return SysUser
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    SysUser get(Integer userId);

    /**
     * 根据用户名查询
     *
     * @param username
     * @return SysUser
     * @author JuLei
     * @since 1.0.0_2019年11月16日
     */
    SysUser getByUsername(String username);

    /**
     * 查询
     *
     * @param sysUserVo
     * @return List<SysUser>
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    List<SysUser> list(SysUserVo sysUserVo);

    /**
     * 分页查询
     *
     * @param sysUserVo
     * @return Page
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    Page pageQuery(SysUserVo sysUserVo);

    /**
     * 修改密码
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    void modifyPass(SysUser bean);

}
