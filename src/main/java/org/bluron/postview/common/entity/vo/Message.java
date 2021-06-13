package org.bluron.postview.common.entity.vo;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 消息封装
 *
 * @author JuLei
 * @since 1.0.0_2019年9月21日
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 8810336298846487856L;

    /**
     * 操作结果
     */
    private boolean result;

    /**
     * 操作结果信息
     */
    private String msg;

    /**
     * 其他参数
     */
    private Object param;

    public Message() {
    }

    public Message(boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public Message(boolean result, String msg, Object param) {
        this.result = result;
        this.msg = msg;
        this.param = param;
    }

    public Message(String msg) {
        this.msg = msg;
    }

    public Message(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
