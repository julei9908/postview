package org.bluron.postview.service.impl;

import org.bluron.postview.common.entity.pojo.Page;
import org.bluron.postview.dao.SysCompanyDao;
import org.bluron.postview.entity.pojo.SysCompany;
import org.bluron.postview.entity.vo.SysCompanyVo;
import org.bluron.postview.service.SysCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司SERVICE实现
 *
 * @author JuLei
 * @since 1.0.0_2019年10月7日
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysCompanyServiceImpl implements SysCompanyService {

    private static final Logger LOG = LoggerFactory.getLogger(SysCompanyServiceImpl.class);

    @Resource
    private SysCompanyDao sysCompanyDao;

    /**
     * 保存
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @Override
    public void save(SysCompany bean) {
        sysCompanyDao.save(bean);
    }

    /**
     * 更新
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @Override
    public void update(SysCompany bean) {
        sysCompanyDao.update(bean);
    }

    /**
     * 根据主键删除
     *
     * @param companyId
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @Override
    public void delete(Integer companyId) {
        sysCompanyDao.delete(companyId);
    }

    /**
     * 根据主键查询
     *
     * @param companyId
     * @return SysCompany
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @Override
    public SysCompany get(Integer companyId) {
        return sysCompanyDao.get(companyId);
    }

    /**
     * 查询
     *
     * @param sysCompanyVo
     * @return List<SysCompany>
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @Override
    public List<SysCompany> list(SysCompanyVo sysCompanyVo) {
        return sysCompanyDao.pageQuery(sysCompanyVo);
    }

    /**
     * 分页查询
     *
     * @param sysCompanyVo
     * @return Page
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    @Override
    public Page pageQuery(SysCompanyVo sysCompanyVo) {
        Page page = null;
        try {
            List<SysCompany> sysCompanys = sysCompanyDao.pageQuery(sysCompanyVo);
            if (null != sysCompanys) {
                page = new Page(sysCompanyVo.getTotal(), sysCompanys);
            } else {
                page = new Page();
            }
        } catch (Exception e) {
            LOG.warn("查询公司异常", e);
        }
        return null == page ? new Page() : page;
    }

}
