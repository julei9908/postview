package org.bluron.postview.entity.pojo;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bluron.postview.common.constant.StateEnum;
import org.bluron.postview.common.util.DateJsonDeserializer;
import org.bluron.postview.common.util.DateJsonSerializer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * 用户POJO
 *
 * @author JuLei
 * @since 1.0.0_2019年10月27日
 */
public class SysUser implements Serializable, UserDetails {

    private static final long serialVersionUID = -7838268193543204479L;

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态 1-启用 0-禁用
     */
    private Integer state;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    private List<GrantedAuthority> grantedAuthority;

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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer value) {
        this.state = value;
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
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.state.intValue() == StateEnum.ENABLE.getState().intValue();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthority;
    }

    public void setGrantedAuthority(List<GrantedAuthority> grantedAuthority) {
        this.grantedAuthority = grantedAuthority;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
