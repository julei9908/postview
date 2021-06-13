package org.bluron.postview.entity.vo;

import com.alibaba.fastjson.JSON;
import org.bluron.postview.common.entity.vo.PageQueryParams;

import java.io.Serializable;

/**
 * 公司VO
 *
 * @author JuLei
 * @since 1.0.0_2019年10月7日
 */
public class SysCompanyVo extends PageQueryParams implements Serializable {

    private static final long serialVersionUID = 2695815447876040677L;

    /**
     * 公司id
     */
    private Integer companyId;
    /**
     * 公司名称
     */
    private String companyName;

    public Integer getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Integer value) {
        this.companyId = value;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String value) {
        this.companyName = value;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
