package cn.sine2cr.zxsiotcloudcommon.common;

/**
 * 错误码
 * @author Sine2cr
 * @Date 2024/1/11
 * @Mail sine2cr@163.com
 **/
public enum ErrorCode {

    SUCCESS(0, "success"),
    PARAMS_ERROR(40000, "请求参数错误"),
    NOT_LOGIN_ERROR(40100, "未登录"),
    NO_AUTH_ERROR(40101, "无权限"),
    NOT_FOUND_ERROR(40400, "请求数据不存在"),
    FORBIDDEN_ERROR(40300, "禁止访问"),
    SYSTEM_ERROR(50000, "系统内部异常"),
    OPERATION_ERROR(50001, "操作失败"),

    PROTOCOL_ERROR(80000, "协议不支持"),
    HTTP_ERROR(80001, "http请求异常"),
    WS_ERROR(80002, "websocket请求异常"),
    WS_NOT_FOUND_ERROR(80003, "websocket请求数据不存在"),
    WS_FORBIDDEN_ERROR(80004, "websocket禁止访问"),
    WS_SYSTEM_ERROR(80005, "websocket系统内部异常"),
    WS_OPERATION_ERROR(80006, "websocket操作失败"),
    DATA_ERROR(80007, "数据异常"),
    TRANSDUCER_DATA_ERROR(80008,"传感器数据异常");


    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }
    public static int getcode(ErrorCode errorCode) {
        return errorCode.getCode();
    }

    public String getMessage() {
        return message;
    }

}
