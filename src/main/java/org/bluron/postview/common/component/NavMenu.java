package org.bluron.postview.common.component;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 导航菜单封装
 *
 * @author JuLei
 * @since 1.0.0_2019年11月29日
 */
public class NavMenu extends MenuTree<NavMenu> implements Serializable {

    private static final long serialVersionUID = 3237223067212447563L;

    /**
     * 地址
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
