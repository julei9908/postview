package org.bluron.postview.entity.pojo;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bluron.postview.common.util.DateJsonDeserializer;
import org.bluron.postview.common.util.DateJsonSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公司POJO
 *
 * @author JuLei
 * @since 1.0.0_2019年10月7日
 */
public class SysCompany implements Serializable {

    private static final long serialVersionUID = 5427125858357832016L;

    /**
     * 公司id
     */
    private Integer companyId;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

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

    @JsonSerialize(using = DateJsonSerializer.class)
    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    @JsonDeserialize(using = DateJsonDeserializer.class)
    public void setCreateTime(LocalDateTime value) {
        this.createTime = value;
    }

    @JsonSerialize(using = DateJsonSerializer.class)
    public LocalDateTime getUpdateTime() {
        return this.updateTime;
    }

    @JsonDeserialize(using = DateJsonDeserializer.class)
    public void setUpdateTime(LocalDateTime value) {
        this.updateTime = value;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
