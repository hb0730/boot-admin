package com.hb0730.web.handler;

import com.hb0730.base.R;
import com.hb0730.base.exception.BasicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * HttpRequestMethodNotSupportedException 异常处理
     *
     * @param e 异常
     * @return {@link R}
     */
    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
    public R<?> handlerHttpRequestMethodNotSupportedException(Exception e) {
        log.error("HttpRequestMethodNotSupportedException", e);
        return R.NG("请求方式不支持" + e.getMessage());
    }

    @ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class)
    public R<?> handlerNoResourceFoundException(Exception e) {
        log.error("NoResourceFoundException", e);
        return R.NG("资源不存在" + e.getMessage());
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public R<?> handleConstraintViolationException(Exception e) {
        log.error("ConstraintViolationException", e);
        return R.NG("参数校验失败" + e.getMessage());
    }

    /**
     * ZoomException 异常处理
     *
     * @param e 异常
     * @return {@link R}
     */
    @ExceptionHandler(BasicException.class)
    public R<?> handlerZoomException(BasicException e) {
        log.error("BasicException", e);
        return R.NG(e.getMessage());
    }

    /**
     * 异常处理
     *
     * @param e 异常
     * @return {@link R}
     */
    @ExceptionHandler(Exception.class)
    public R<?> handlerException(Exception e) {
        log.error("系统异常", e);
        return R.error(500, "系统异常");
    }
}
