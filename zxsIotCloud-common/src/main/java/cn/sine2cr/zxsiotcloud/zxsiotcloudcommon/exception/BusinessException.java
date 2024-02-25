package cn.sine2cr.zxsiotcloud.zxsiotcloudcommon.exception;


import cn.sine2cr.zxsiotcloud.zxsiotcloudcommon.common.ErrorCode;

/**
 * 自定义业务异常
 * @author Sine2cr
 * @Date 2024/1/11
 * @Mail sine2cr@163.com
 **/
public class BusinessException extends RuntimeException{
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
