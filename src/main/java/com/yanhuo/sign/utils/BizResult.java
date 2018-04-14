package com.yanhuo.sign.utils;

import java.io.Serializable;
import java.util.Map;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: 扩展APIResult，增加补充数据Map
 * @date 2018/3/20 上午11:17
 */
public class BizResult<T> implements Serializable {


    /**
     * 是否成功
     */
    private boolean success = false;

    /**
     * 返回结果码
     */
    private String code;

    /**
     * 返回结果描述
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 补充数据
     */
    private Map<String,Object> extraInfo;

    public static <T> BizResult<T> create() {
        return new BizResult<T>();
    }


    /**
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BizResult<T> create(T data) {
        BizResult<T> bizResult = create();
        bizResult.setSuccess(true);
        bizResult.setData(data);
        return bizResult;
    }

    /**
     * 接口调用成功时也需要code和message的场景
     * @param data
     * @param code
     * @param message
     * @return
     */
    public static <T> BizResult<T> create(T data, String code, String message) {
        BizResult<T> result = BizResult.create();
        result.setSuccess(true);
        result.setData(data);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 任意场景
     * @param isSuccess
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static <T> BizResult<T> create(T data, boolean isSuccess, String code, String message) {
        BizResult<T> result = BizResult.create();
        result.setSuccess(isSuccess);
        result.setData(data);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * @param data
     * @param extraInfo
     * @param <T>
     * @return
     */
    public static <T> BizResult<T> create(T data, Map<String,Object> extraInfo){
        BizResult<T> bizResult = create();
        bizResult.setSuccess(true);
        bizResult.setData(data);
        bizResult.setExtraInfo(extraInfo);
        return bizResult;
    }

    /**
     * @param data
     * @param extraInfo
     * @param isSuccess
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> BizResult<T> create(T data, Map<String,Object> extraInfo, boolean isSuccess, String code, String message){
        BizResult<T> bizResult = create();
        bizResult.setSuccess(true);
        bizResult.setData(data);
        bizResult.setExtraInfo(extraInfo);
        bizResult.setSuccess(isSuccess);
        bizResult.setCode(code);
        bizResult.setMessage(message);
        return bizResult;
    }

    /**
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> BizResult<T> create(String code, String message) {
        BizResult<T> bizResult = create();
        bizResult.setSuccess(false);
        bizResult.setCode(code);
        bizResult.setMessage(message);
        return bizResult;
    }

    public static <T> BizResult<T> create(String code, String message,T data) {
        BizResult<T> bizResult = create();
        bizResult.setSuccess(false);
        bizResult.setData(data);
        bizResult.setCode(code);
        bizResult.setMessage(message);
        return bizResult;
    }

    public Map<String, Object> getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(Map<String, Object> extraInfo) {
        this.extraInfo = extraInfo;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
