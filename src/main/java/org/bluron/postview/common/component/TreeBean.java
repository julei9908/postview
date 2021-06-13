package org.bluron.postview.common.component;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

/**
 * 树形控件封装
 *
 * @author JuLei
 * @since 1.0.0_2019年11月07日
 */
public class TreeBean implements Serializable {

    private static final long serialVersionUID = 2596192712534999916L;

    /**
     * id
     */
    private Integer id;
    /**
     * 名称
     */
    private String label;
    /**
     * 子节点
     */
    private List<TreeBean> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TreeBean> getChildren() {
        return children;
    }

    public void setChildren(List<TreeBean> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
