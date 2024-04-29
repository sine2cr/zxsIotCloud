package cn.sine2cr.zxsiotcloudcommon.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 基本通用返回类
 *
 * @author Sine2cr
 * @version 1.0.0
 * @Since 1.0.0
 * @Date 2024/01/13
 * @Mail sine2cr@163.com
 **/
@Data
public class BaseResponse<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    /**
     * @param code
     * @param data
     * @param message
     */
    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    /**
     * @param code
     * @param data
     */
    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    /**
     * @param errorCode
     */
    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}
