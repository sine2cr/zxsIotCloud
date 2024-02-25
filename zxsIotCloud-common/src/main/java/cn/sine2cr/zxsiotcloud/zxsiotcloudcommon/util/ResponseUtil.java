package cn.sine2cr.zxsiotcloud.zxsiotcloudcommon.util;


import cn.sine2cr.zxsiotcloud.zxsiotcloudcommon.common.BaseResponse;
import cn.sine2cr.zxsiotcloud.zxsiotcloudcommon.common.ErrorCode;

/**
 * @author Sine2cr
 * @Date 2024/1/11
 * @Mail sine2cr@163.com
 **/
public class ResponseUtil {
    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "success");
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @return
     */
    public static BaseResponse error(int code, String message) {
        return new BaseResponse(code, null, message);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode, String message) {
        return new BaseResponse(errorCode.getCode(), null, message);
    }
}
