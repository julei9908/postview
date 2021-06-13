package org.bluron.postview.controller;

import org.bluron.postview.common.component.BaseBean;
import org.bluron.postview.common.component.TreeBean;
import org.bluron.postview.common.constant.StateEnum;
import org.bluron.postview.common.entity.pojo.Page;
import org.bluron.postview.common.entity.vo.Message;
import org.bluron.postview.entity.pojo.SysRole;
import org.bluron.postview.entity.vo.SysRoleVo;
import org.bluron.postview.service.SysRoleService;
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
 * 角色CONTROLLER
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
@RestController
@RequestMapping(value = "/sysRole")
public class SysRoleController {

    private static final Logger LOG = LoggerFactory.getLogger(SysRoleController.class);

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 新增
     *
     * @param bean
     * @return Message
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @PostMapping(value = "/save")
    public Message save(SysRole bean) {
        Message message = new Message(false, "保存失败");
        try {
            sysRoleService.save(bean);
            message = new Message(true, "保存成功");
        } catch (Exception e) {
            LOG.warn("保存异常：" + bean.toString(), e);
        }
        return message;
    }

    /**
     * 更新
     *
     * @param bean
     * @return Message
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @PostMapping(value = "/update")
    public Message update(SysRole bean) {
        Message message = new Message(false, "修改失败");
        try {
            sysRoleService.update(bean);
            message = new Message(true, "修改成功");
        } catch (Exception e) {
            LOG.warn("修改异常" + bean.toString(), e);
        }
        return message;
    }

    /**
     * 根据主键查询
     *
     * @param roleId
     * @return SysRole
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @PostMapping(value = "/get")
    public SysRole get(Integer roleId) {
        try {
            return sysRoleService.get(roleId);
        } catch (Exception e) {
            LOG.warn("根据主键查询SysRole异常：" + roleId, e);
        }
        return null;
    }

    /**
     * 查询
     *
     * @param sysRoleVo
     * @return List<SysRole>
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @PostMapping(value = "/list")
    public List<SysRole> list(SysRoleVo sysRoleVo) {
        return sysRoleService.list(sysRoleVo);
    }

    /**
     * 分页查询
     *
     * @param sysRoleVo
     * @return Page
     * @author JuLei
     * @since 1.0.0_2019年10月27日
     */
    @PostMapping(value = "/pageQuery")
    public Page pageQuery(SysRoleVo sysRoleVo) {
        return sysRoleService.pageQuery(sysRoleVo);
    }

    /**
     * 角色下拉框
     *
     * @param sysRoleVo
     * @return List<BaseBean>
     * @author JuLei
     * @since 1.0.0_2019年11月07日
     */
    @PostMapping(value = "/selectList")
    public List<BaseBean> selectList(SysRoleVo sysRoleVo) {
        List<SysRole> lists = sysRoleService.list(sysRoleVo);
        List<BaseBean> BaseBeans = new ArrayList<>();
        for (SysRole sysRole : lists) {
            BaseBean BaseBean = new BaseBean();
            BaseBean.setLabel(sysRole.getRoleName());
            BaseBean.setId(sysRole.getRoleId());
            BaseBeans.add(BaseBean);
        }
        return BaseBeans;
    }

    /**
     * 树形角色查询
     *
     * @param sysRoleVo
     * @return List<TreeBean>
     * @author JuLei
     * @since 1.0.0_2019年11月07日
     */
    @PostMapping(value = "/treeList")
    public List<TreeBean> treeList(SysRoleVo sysRoleVo) {
        List<SysRole> lists = sysRoleService.list(sysRoleVo);
        List<TreeBean> treeBeans = new ArrayList<>();
        for (SysRole sysRole : lists) {
            TreeBean treeBean = new TreeBean();
            treeBean.setId(sysRole.getRoleId());
            treeBean.setLabel(sysRole.getRoleName());
            treeBean.setChildren(new ArrayList<>());
            treeBeans.add(treeBean);
        }
        return treeBeans;
    }

    /**
     * 启用
     *
     * @param roleId
     * @return Message
     * @author JuLei
     * @since 1.0.0_2019年11月7日
     */
    @GetMapping(value = "/enable")
    public Message enable(Integer roleId) {
        Message message = new Message(false, "操作失败");
        try {
            SysRole sysRole = new SysRole();
            sysRole.setRoleId(roleId);
            sysRole.setState(StateEnum.ENABLE.getState());
            sysRoleService.updateIfNotNull(sysRole);
            message = new Message(true, "操作成功");
        } catch (Exception e) {
            LOG.warn("禁用角色异常", e);
        }
        return message;
    }

    /**
     * 禁用
     *
     * @param roleId
     * @return Message
     * @author JuLei
     * @since 1.0.0_2019年11月7日
     */
    @GetMapping(value = "/disable")
    public Message disable(Integer roleId) {
        Message message = new Message(false, "操作失败");
        try {
            SysRole sysRole = new SysRole();
            sysRole.setRoleId(roleId);
            sysRole.setState(StateEnum.DISABLE.getState());
            sysRoleService.updateIfNotNull(sysRole);
            message = new Message(true, "操作成功");
        } catch (Exception e) {
            LOG.warn("启用角色异常", e);
        }
        return message;
    }

}
