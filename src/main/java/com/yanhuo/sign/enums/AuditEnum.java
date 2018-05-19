package com.yanhuo.sign.enums;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: TODO
 * @date 2018/4/26 下午4:57
 */
public enum AuditEnum {
    TO_AUDIT(0,"待审核"),
    AUDIT_PASS(1,"审核通过"),
    AUDIT_REJECT(2,"审核拒绝"),
    ;
    private Integer code;

    private String message;

    AuditEnum(Integer code, String message) {
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
