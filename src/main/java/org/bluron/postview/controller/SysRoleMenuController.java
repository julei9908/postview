package org.bluron.postview.controller;

import org.bluron.postview.common.entity.vo.Message;
import org.bluron.postview.entity.pojo.SysRoleMenu;
import org.bluron.postview.service.SysRoleMenuService;
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
 * 角色资源CONTROLLER
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
@RestController
@RequestMapping(value = "/sysRoleMenu")
public class SysRoleMenuController {

    private static final Logger LOG = LoggerFactory.getLogger(SysRoleMenuController.class);

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 分配资源
     *
     * @param roleId
     * @param menuIds
     * @return void
     * @author JuLei
     * @since 1.0.0_2019年11月29日
     */
    @PostMapping(value = "/save")
    public Message save(Integer roleId, String menuIds) {
        Message message = new Message(false, "分配失败");
        try {
            sysRoleMenuService.delete(roleId);
            String[] split = menuIds.split(",");
            for (String menuId : split) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRoleId(roleId);
                sysRoleMenu.setMenuId(Integer.valueOf(menuId));
                sysRoleMenuService.save(sysRoleMenu);
            }
            message = new Message(true, "分配成功");
        } catch (Exception e) {
            LOG.warn("分配角色资源失败", e);
        }
        return message;
    }

    /**
     * 查询角色资源
     *
     * @param roleId
     * @return List<Integer>
     * @author JuLei
     * @since 1.0.0_2019年11月29日
     */
    @GetMapping(value = "/getMenusByRoleId")
    public List<Integer> getMenusByRoleId(Integer roleId) {
        List<SysRoleMenu> menuByRoleId = sysRoleMenuService.getMenuByRoleId(roleId);
        List<Integer> lists = new ArrayList<>();
        for (SysRoleMenu sysRoleMenu : menuByRoleId) {
            lists.add(sysRoleMenu.getMenuId());
        }
        return lists;
    }

}
