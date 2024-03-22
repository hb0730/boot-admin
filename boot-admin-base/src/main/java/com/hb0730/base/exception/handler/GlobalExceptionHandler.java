package com.hb0730.base.exception.handler;

import com.hb0730.base.R;
import com.hb0730.base.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/23
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常
     *
     * @param e 异常
     * @return R
     */
    @ExceptionHandler(Exception.class)
    public R<?> handler(Exception e) {
        log.error("系统异常", e);
        return R.NG("系统异常,请稍后重试");
    }

    /**
     * 请求异常
     *
     * @param e 异常
     * @return R
     */
    @ExceptionHandler(BadRequestException.class)
    public R<?> badRequestHandler(BadRequestException e) {
        Integer code = e.getCode();
        if (code == null) {
            return R.NG(e.getMessage());
        }
        return R.NG(code, e.getMessage());
    }

    /**
     * 参数校验失败
     *
     * @param e 异常
     * @return R
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<?> methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        // 打印堆栈信息
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        String message = objectError.getDefaultMessage();
        if (objectError instanceof FieldError) {
            message = ((FieldError) objectError).getField() + ": " + message;
        }
        return R.NG(message);
    }
}
