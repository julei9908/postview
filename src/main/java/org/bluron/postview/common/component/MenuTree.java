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
public class MenuTree<T> extends BaseBean implements Serializable {

    private static final long serialVersionUID = -1709551243441824470L;

    /**
     * 子节点
     */
    private List<T> children;

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
