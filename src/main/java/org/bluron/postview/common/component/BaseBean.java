package org.bluron.postview.common.component;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 基础封装
 *
 * @author JuLei
 * @since 1.0.0_2019年11月29日
 */
public class BaseBean implements Serializable {

    private static final long serialVersionUID = -6500589060552521437L;

    /**
     * id
     */
    private Object id;
    /**
     * 名称
     */
    private String label;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
