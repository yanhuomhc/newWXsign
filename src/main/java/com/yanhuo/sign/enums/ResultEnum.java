package com.yanhuo.sign.enums;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: 枚举类
 * @date 2018/3/20 下午5:18
 */
public enum  ResultEnum {
    NOT_SIGN(0,"未签到"),
    SIGN(1,"已签到"),
    SIGN_FALSE(2,"签到失败"),
    SIGN_TURE(3,"签到成功"),
            ;
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
