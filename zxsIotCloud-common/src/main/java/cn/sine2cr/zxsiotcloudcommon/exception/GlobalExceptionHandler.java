package cn.sine2cr.zxsiotcloudcommon.exception;


import cn.sine2cr.zxsiotcloudcommon.common.BaseResponse;
import cn.sine2cr.zxsiotcloudcommon.common.ErrorCode;
import cn.sine2cr.zxsiotcloudcommon.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * @author Sine2cr
 * @Date 2024/1/11
 * @Mail sine2cr@163.com
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("businessException: " + e.getMessage(), e);
        return ResponseUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return ResponseUtil.error(ErrorCode.SYSTEM_ERROR, e.getMessage());
    }
}
