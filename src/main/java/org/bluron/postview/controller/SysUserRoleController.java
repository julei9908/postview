package org.bluron.postview.controller;

import org.bluron.postview.common.entity.vo.Message;
import org.bluron.postview.entity.pojo.SysUserRole;
import org.bluron.postview.service.SysUserRoleService;
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
 * 用户角色CONTROLLER
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
@RestController
@RequestMapping(value = "/sysUserRole")
public class SysUserRoleController {

    private static final Logger LOG = LoggerFactory.getLogger(SysUserRoleController.class);

    @Resource
    private SysUserRoleService sysUserRoleService;

    /**
     * 分配角色
     *
     * @param userId
     * @param roleId
     * @return void
     * @author JuLei
     * @since 1.0.0_2019年11月29日
     */
    @PostMapping(value = "/save")
    public Message save(Integer userId, Integer roleId) {
        Message message = new Message(false, "分配失败");
        try {
            SysUserRole sysUserRole = sysUserRoleService.getRoleByUserId(userId);
            if (null != sysUserRole) {
                sysUserRole.setRoleId(roleId);
                sysUserRoleService.update(sysUserRole);
            } else {
                SysUserRole bean = new SysUserRole();
                bean.setUserId(userId);
                bean.setRoleId(roleId);
                sysUserRoleService.save(bean);
            }
            message = new Message(true, "分配成功");
        } catch (Exception e) {
            LOG.warn("分配用户角色失败", e);
        }
        return message;
    }

    /**
     * 查询用户角色
     *
     * @param userId
     * @return List<Integer>
     * @author JuLei
     * @since 1.0.0_2019年11月29日
     */
    @GetMapping(value = "/getRoleByUserId")
    public List<Integer> getRoleByUserId(Integer userId) {
        List<Integer> role = new ArrayList<>();
        SysUserRole sysUserRole = sysUserRoleService.getRoleByUserId(userId);
        if (null != sysUserRole) {
            role.add(sysUserRole.getRoleId());
        }
        return role;
    }

}
