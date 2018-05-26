package com.yanhuo.sign.enums;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: 枚举类
 * @date 2018/3/20 下午5:18
 */
public enum  PowerEnum {
    STUDENT(3,"学生"),
    ADMIN(1,"管理员"),
    TEACHER(2,"老师"),

            ;
    private Integer code;

    private String message;

    PowerEnum(Integer code, String message) {
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
