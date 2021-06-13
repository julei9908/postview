package org.bluron.postview.controller;

import org.bluron.postview.common.component.BaseBean;
import org.bluron.postview.common.entity.pojo.Page;
import org.bluron.postview.common.entity.vo.Message;
import org.bluron.postview.entity.pojo.SysCompany;
import org.bluron.postview.entity.vo.SysCompanyVo;
import org.bluron.postview.service.SysCompanyService;
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
 * 公司CONTROLLER
 *
 * @author JuLei
 * @since 1.0.0_2019年10月7日
 */
@RestController
@RequestMapping(value = "/sysCompany")
public class SysCompanyController {

    private static final Logger LOG = LoggerFactory.getLogger(SysCompanyController.class);

    @Resource
    private SysCompanyService sysCompanyService;

    /**
     * 新增
     *
     * @param bean
     * @return Message
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @PostMapping(value = "/save")
    public Message save(SysCompany bean) {
        Message message = new Message(false, "保存失败");
        try {
            sysCompanyService.save(bean);
            message = new Message(true, "保存成功");
        } catch (Exception e) {
            LOG.warn("保存公司异常：" + bean.toString(), e);
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
    @PostMapping(value = "/update")
    public Message update(SysCompany bean) {
        Message message = new Message(false, "修改失败");
        try {
            sysCompanyService.update(bean);
            message = new Message(true, "修改成功");
        } catch (Exception e) {
            LOG.warn("修改公司异常" + bean.toString(), e);
        }
        return message;
    }

    /**
     * 根据主键删除
     *
     * @param companyId
     * @return Message
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @GetMapping(value = "/delete")
    public Message delete(Integer companyId) {
        Message message = new Message(false, "删除失败");
        try {
            sysCompanyService.delete(companyId);
            message = new Message(true, "删除成功");
        } catch (Exception e) {
            LOG.warn("删除公司异常：" + companyId, e);
        }
        return message;
    }

    /**
     * 根据主键查询
     *
     * @param companyId
     * @return SysCompany
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @PostMapping(value = "/get")
    public SysCompany get(Integer companyId) {
        try {
            return sysCompanyService.get(companyId);
        } catch (Exception e) {
            LOG.warn("根据主键查询公司异常：" + companyId, e);
        }
        return null;
    }

    /**
     * 查询
     *
     * @param sysCompanyVo
     * @return List<SysCompany>
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @PostMapping(value = "/list")
    public List<SysCompany> list(SysCompanyVo sysCompanyVo) {
        return sysCompanyService.list(sysCompanyVo);
    }

    /**
     * 分页查询
     *
     * @param sysCompanyVo
     * @return Page
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @PostMapping(value = "/pageQuery")
    public Page pageQuery(SysCompanyVo sysCompanyVo) {
        return sysCompanyService.pageQuery(sysCompanyVo);
    }

    /**
     * 公司下拉框
     *
     * @param sysCompanyVo
     * @return Page
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @PostMapping(value = "/selectList")
    public List<BaseBean> selectList(SysCompanyVo sysCompanyVo) {
        List<SysCompany> lists = sysCompanyService.list(sysCompanyVo);
        List<BaseBean> BaseBeans = new ArrayList<>();
        for (SysCompany list : lists) {
            BaseBean BaseBean = new BaseBean();
            BaseBean.setLabel(list.getCompanyName());
            BaseBean.setId(list.getCompanyId());
            BaseBeans.add(BaseBean);
        }
        return BaseBeans;
    }

}
