package org.bluron.postview.service;

import org.bluron.postview.common.entity.pojo.Page;
import org.bluron.postview.entity.pojo.SysCompany;
import org.bluron.postview.entity.vo.SysCompanyVo;

import java.util.List;

/**
 * 公司SERVICE
 *
 * @author JuLei
 * @since 1.0.0_2019年10月7日
 */
public interface SysCompanyService {

    /**
     * 保存
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    void save(SysCompany bean);

    /**
     * 更新
     *
     * @param bean
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    void update(SysCompany bean);

    /**
     * 根据主键删除
     *
     * @param companyId
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    void delete(Integer companyId);

    /**
     * 根据主键查询
     *
     * @param companyId
     * @return SysCompany
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    SysCompany get(Integer companyId);

    /**
     * 查询
     *
     * @param sysCompanyVo
     * @return List<SysCompany>
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    List<SysCompany> list(SysCompanyVo sysCompanyVo);

    /**
     * 分页查询
     *
     * @param sysCompanyVo
     * @return Page
     * @author JuLei
     * @since 1.0.0_2019年10月7日
     */
    Page pageQuery(SysCompanyVo sysCompanyVo);

}
