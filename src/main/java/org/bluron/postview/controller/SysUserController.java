package org.bluron.postview.controller;

import org.bluron.postview.common.component.BaseBean;
import org.bluron.postview.common.constant.StateEnum;
import org.bluron.postview.common.entity.pojo.Page;
import org.bluron.postview.common.entity.vo.Message;
import org.bluron.postview.entity.pojo.SysUser;
import org.bluron.postview.entity.vo.SysUserVo;
import org.bluron.postview.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户CONTROLLER
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
@RestController
@RequestMapping(value = "/sysUser")
public class SysUserController {

    private static final Logger LOG = LoggerFactory.getLogger(SysUserController.class);

    @Resource
    private SysUserService sysUserService;

    /**
     * 更新
     *
     * @param bean
     * @return Message
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @PostMapping(value = "/update")
    public Message update(SysUser bean) {
        Message message = new Message(false, "修改失败");
        try {
            sysUserService.update(bean);
            message = new Message(true, "修改成功");
        } catch (Exception e) {
            LOG.warn("修改异常" + bean.toString(), e);
        }
        return message;
    }

    /**
     * 根据主键查询
     *
     * @param userId
     * @return SysUser
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @PostMapping(value = "/get")
    public SysUser get(Integer userId) {
        try {
            return sysUserService.get(userId);
        } catch (Exception e) {
            LOG.warn("根据主键查询SysUser异常：" + userId, e);
        }
        return null;
    }

    /**
     * 查询
     *
     * @param sysUserVo
     * @return List<SysUser>
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @PostMapping(value = "/list")
    public List<SysUser> list(SysUserVo sysUserVo) {
        return sysUserService.list(sysUserVo);
    }

    /**
     * 分页查询
     *
     * @param sysUserVo
     * @return Page
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @PostMapping(value = "/pageQuery")
    public Page pageQuery(SysUserVo sysUserVo) {
        return sysUserService.pageQuery(sysUserVo);
    }

    /**
     * 启用用户
     *
     * @param userId
     * @return Message
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @GetMapping(value = "/enable")
    public Message enable(Integer userId) {
        Message message = new Message(false, "修改失败");
        try {
            SysUser sysUser = new SysUser();
            sysUser.setUserId(userId);
            sysUser.setState(StateEnum.ENABLE.getState());
            sysUserService.updateIfNotNull(sysUser);
            message = new Message(true, "修改成功");
        } catch (Exception e) {
            LOG.warn("禁用用户失败", e);
        }
        return message;
    }

    /**
     * 禁用用户
     *
     * @param userId
     * @return Message
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @GetMapping(value = "/disable")
    public Message disable(Integer userId) {
        Message message = new Message(false, "修改失败");
        try {
            SysUser sysUser = new SysUser();
            sysUser.setUserId(userId);
            sysUser.setState(StateEnum.DISABLE.getState());
            sysUserService.updateIfNotNull(sysUser);
            message = new Message(true, "修改成功");
        } catch (Exception e) {
            LOG.warn("禁用用户失败", e);
        }
        return message;
    }

    /**
     * 修改用户密码
     *
     * @param bean
     * @return Message
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @PostMapping(value = "/modifyPass")
    public Message modifyPass(SysUser bean) {
        Message message = new Message(false, "修改成功");
        try {
            sysUserService.modifyPass(bean);
            message = new Message(true, "修改成功");
        } catch (Exception e) {
            LOG.warn("修改用户密码成功", e);
        }
        return message;
    }

    /**
     * 用户下拉框
     *
     * @param sysUserVo
     * @return List<BaseBean>
     * @author JuLei
     * @since 1.0.0_2019年11月26日
     */
    @PostMapping(value = "/selectList")
    public List<BaseBean> selectList(SysUserVo sysUserVo) {
        List<SysUser> lists = sysUserService.list(sysUserVo);
        List<BaseBean> BaseBeans = new ArrayList<>();
        for (SysUser sysUser : lists) {
            BaseBean BaseBean = new BaseBean();
            BaseBean.setLabel(sysUser.getUsername());
            BaseBean.setId(sysUser.getUserId());
            BaseBeans.add(BaseBean);
        }
        return BaseBeans;
    }

}
