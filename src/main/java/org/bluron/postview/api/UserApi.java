package org.bluron.postview.api;

import org.bluron.postview.common.entity.vo.Message;
import org.bluron.postview.controller.SysUserController;
import org.bluron.postview.entity.pojo.SysUser;
import org.bluron.postview.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户接口
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
@RestController
@RequestMapping(value = "/api")
public class UserApi {

    private static final Logger LOG = LoggerFactory.getLogger(SysUserController.class);

    @Resource
    private SysUserService sysUserService;

    /**
     * 用户注册
     *
     * @param bean
     * @return Message
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @PostMapping(value = "/register")
    public Message register(SysUser bean) {
        Message message = new Message(false, "注册失败");
        try {
            SysUser sysUser = sysUserService.getByUsername(bean.getUsername());
            if (null != sysUser) {
                return new Message(false, "用户名已存在");
            }
            sysUserService.save(bean);
            message = new Message(true, "注册成功");
        } catch (Exception e) {
            LOG.warn("注册用户异常：" + bean.toString(), e);
        }
        return message;
    }

}
