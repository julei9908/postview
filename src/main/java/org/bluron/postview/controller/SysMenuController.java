package org.bluron.postview.controller;

import org.apache.commons.lang3.StringUtils;
import org.bluron.postview.common.component.BaseBean;
import org.bluron.postview.common.component.MenuTree;
import org.bluron.postview.common.component.NavMenu;
import org.bluron.postview.common.constant.StateEnum;
import org.bluron.postview.common.entity.pojo.Page;
import org.bluron.postview.common.entity.vo.Message;
import org.bluron.postview.entity.pojo.SysMenu;
import org.bluron.postview.entity.pojo.SysUser;
import org.bluron.postview.entity.pojo.SysUserRole;
import org.bluron.postview.entity.vo.SysMenuVo;
import org.bluron.postview.service.SysMenuService;
import org.bluron.postview.service.SysUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单CONTROLLER
 *
 * @author JuLei
 * @since 1.0.0_2019年10月7日
 */
@RestController
@RequestMapping(value = "/sysMenu")
public class SysMenuController {

    private static final Logger LOG = LoggerFactory.getLogger(SysMenuController.class);

    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    /**
     * 新增
     *
     * @param bean
     * @return Message
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @PostMapping(value = "/save")
    public Message save(SysMenu bean) {
        Message message = new Message(false, "保存失败");
        try {
            sysMenuService.save(bean);
            message = new Message(true, "保存成功");
        } catch (Exception e) {
            LOG.warn("保存菜单异常：" + bean.toString(), e);
        }
        return message;
    }

    /**
     * 更新
     *
     * @param bean
     * @return Message
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @PostMapping(value = "/updateIfNotNull")
    public Message updateIfNotNull(SysMenu bean) {
        Message message = new Message(false, "修改失败");
        try {
            sysMenuService.updateIfNotNull(bean);
            message = new Message(true, "修改成功");
        } catch (Exception e) {
            LOG.warn("修改菜单失败：" + bean.toString(), e);
        }
        return message;
    }

    /**
     * 启用
     *
     * @param menuId
     * @return Message
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @GetMapping(value = "/enable")
    public Message enable(Integer menuId) {
        Message message = new Message(false, "操作失败");
        try {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setMenuId(menuId);
            sysMenu.setState(StateEnum.ENABLE.getState());
            sysMenuService.updateIfNotNull(sysMenu);
            message = new Message(true, "操作成功");
        } catch (Exception e) {
            LOG.warn("启用菜单异常：" + menuId, e);
        }
        return message;
    }

    /**
     * 禁用
     *
     * @param menuId
     * @return Message
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @GetMapping(value = "/disable")
    public Message disable(Integer menuId) {
        Message message = new Message(false, "操作失败");
        try {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setMenuId(menuId);
            sysMenu.setState(StateEnum.DISABLE.getState());
            sysMenuService.updateIfNotNull(sysMenu);
            message = new Message(true, "操作成功");
        } catch (Exception e) {
            LOG.warn("启用菜单异常：" + menuId, e);
        }
        return message;
    }

    /**
     * 根据主键查询
     *
     * @param menuId
     * @return SysMenu
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @PostMapping(value = "/get")
    public SysMenu get(Integer menuId) {
        try {
            return sysMenuService.get(menuId);
        } catch (Exception e) {
            LOG.warn("根据主键查询菜单异常：" + menuId, e);
        }
        return null;
    }

    /**
     * 查询
     *
     * @param sysMenuVo
     * @return List<SysMenu>
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @PostMapping(value = "/list")
    public List<SysMenu> list(SysMenuVo sysMenuVo) {
        return sysMenuService.list(sysMenuVo);
    }

    /**
     * 分页查询
     *
     * @param sysMenuVo
     * @return Page
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @PostMapping(value = "/pageQuery")
    public Page pageQuery(SysMenuVo sysMenuVo) {
        return sysMenuService.pageQuery(sysMenuVo);
    }

    /**
     * 菜单下拉框
     *
     * @param sysMenuVo
     * @return List<BaseBean>
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @PostMapping(value = "/selectList")
    public List<BaseBean> selectList(SysMenuVo sysMenuVo) {
        List<SysMenu> lists = sysMenuService.list(sysMenuVo);
        List<BaseBean> BaseBeans = new ArrayList<>();
        for (SysMenu list : lists) {
            BaseBean BaseBean = new BaseBean();
            BaseBean.setLabel(list.getMenuName());
            BaseBean.setId(list.getMenuId());
            BaseBeans.add(BaseBean);
        }
        return BaseBeans;
    }

    /**
     * 菜单下拉框查询
     *
     * @param sysMenuVo
     * @return List<MenuTree>
     * @author JuLei
     * @since 1.0.0_2019年11月07日
     */
    @PostMapping(value = "/menuList")
    public List<MenuTree> menuList(SysMenuVo sysMenuVo) {
        List<SysMenu> menus = sysMenuService.list(sysMenuVo);
        List<MenuTree> menuTrees = new ArrayList<>();
        for (SysMenu menu : menus) {
            MenuTree menuTree = new MenuTree();
            menuTree.setId(menu.getMenuId());
            menuTree.setLabel(menu.getMenuName());
            menuTrees.add(menuTree);
        }
        return menuTrees;
    }

    /**
     * 导航菜单查询
     *
     * @param sysMenuVo
     * @return Page
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @PostMapping(value = "/navMenu")
    public List<NavMenu> navMenu(SysMenuVo sysMenuVo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = (SysUser) authentication.getPrincipal();
        SysUserRole sysUserRole = sysUserRoleService.getRoleByUserId(sysUser.getUserId());
        List<SysMenu> origin = sysMenuService.navMenu(sysUserRole.getRoleId());
        List<NavMenu> menuList = new ArrayList<>();
        for (SysMenu sysMenu : origin) {
            if (sysMenu.getSupMenuId() == null) {
                NavMenu navMenu = new NavMenu();
                navMenu.setId(sysMenu.getMenuId());
                navMenu.setLabel(sysMenu.getMenuName());
                navMenu.setIcon(sysMenu.getMenuIcon());
                navMenu.setUrl(sysMenu.getMenuUrl());
                menuList.add(navMenu);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (NavMenu navMenu : menuList) {
            navMenu.setChildren(navMenuChildren((Integer) navMenu.getId(), origin));
        }
        return menuList;
    }

    /**
     * 递归获取菜单树
     *
     * @param menuId
     * @param origin
     * @return List<SysMenu>
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    private List<NavMenu> navMenuChildren(Integer menuId, List<SysMenu> origin) {
        // 子菜单
        List<NavMenu> childList = new ArrayList<>();
        for (SysMenu sysMenu : origin) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (sysMenu.getSupMenuId() != null && sysMenu.getSupMenuId().intValue() == menuId.intValue()) {
                NavMenu navMenu = new NavMenu();
                navMenu.setId(sysMenu.getMenuId());
                navMenu.setLabel(sysMenu.getMenuName());
                navMenu.setUrl(sysMenu.getMenuUrl());
                navMenu.setIcon(sysMenu.getMenuIcon());
                childList.add(navMenu);
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (NavMenu navMenu : childList) {// 没有url子菜单还有子菜单
            if (StringUtils.isBlank(navMenu.getUrl())) {
                // 递归
                navMenu.setChildren(navMenuChildren((Integer) navMenu.getId(), origin));
            }
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    /**
     * 菜单树查询
     *
     * @param sysMenuVo
     * @return Page
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @PostMapping(value = "/menuTree")
    public List<MenuTree> menuTree(SysMenuVo sysMenuVo) {
        List<SysMenu> origin = sysMenuService.list(sysMenuVo);
        List<MenuTree> menuList = new ArrayList<>();
        for (SysMenu sysMenu : origin) {
            if (sysMenu.getSupMenuId() == null) {
                MenuTree menuTree = new MenuTree();
                menuTree.setId(sysMenu.getMenuId());
                menuTree.setLabel(sysMenu.getMenuName());
                menuList.add(menuTree);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (MenuTree menuTree : menuList) {
            menuTree.setChildren(getChildMenus((Integer) menuTree.getId(), origin));
        }
        return menuList;
    }

    /**
     * 递归获取菜单树
     *
     * @param menuId
     * @param origin
     * @return List<SysMenu>
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    private List<MenuTree> getChildMenus(Integer menuId, List<SysMenu> origin) {
        // 子菜单
        List<MenuTree> childList = new ArrayList<>();
        for (SysMenu sysMenu : origin) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (sysMenu.getSupMenuId() != null && sysMenu.getSupMenuId().intValue() == menuId.intValue()) {
                MenuTree menuTree = new MenuTree();
                menuTree.setId(sysMenu.getMenuId());
                menuTree.setLabel(sysMenu.getMenuName());
                childList.add(menuTree);
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (MenuTree menuTree : childList) {// 没有url子菜单还有子菜单
            SysMenu sysMenu = sysMenuService.get((Integer) menuTree.getId());
            if (StringUtils.isBlank(sysMenu.getMenuUrl())) {
                // 递归
                menuTree.setChildren(getChildMenus((Integer) menuTree.getId(), origin));
            }
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

}
