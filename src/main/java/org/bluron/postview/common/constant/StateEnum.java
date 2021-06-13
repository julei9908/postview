package org.bluron.postview.common.constant;

/**
 * 状态枚举
 *
 * @author JuLei
 * @since 1.0.0_2019年9月21日
 */
public enum StateEnum {

    ENABLE(1), DISABLE(0);

    private Integer state;

    StateEnum(int state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}
