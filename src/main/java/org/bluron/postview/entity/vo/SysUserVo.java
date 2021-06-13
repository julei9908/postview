package org.bluron.postview.entity.vo;

import com.alibaba.fastjson.JSON;
import org.bluron.postview.common.entity.vo.PageQueryParams;

import java.io.Serializable;

/**
 * 用户VO
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
public class SysUserVo extends PageQueryParams implements Serializable {

    private static final long serialVersionUID = -4503668934923702311L;

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 状态 1-启用 0-禁用
     */
    private Integer state;

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer value) {
        this.userId = value;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String value) {
        this.username = value;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer value) {
        this.state = value;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
