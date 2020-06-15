package com.welisit.servicebase.exception;

import com.welisit.commonutils.ExceptionUtil;
import com.welisit.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author welisit
 * @Description 全局异常处理类
 * @create 2020-06-14 10:48
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public R handle(Exception e) {
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return R.error().message(e.getMessage());
    }

    @ExceptionHandler(value = ApiException.class)
    public R apiException(ApiException e) {
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return R.error().message(e.getMessage()).code(e.getCode());
    }
}
