package com.hb0730.boot.admin.handler;

import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.result.Results;
import com.hb0730.boot.admin.exceptions.LoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获
 *
 * @author bing_huang
 * @since 3.0.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 用户登录异常
     *
     * @param e LoginException
     * @return 响应信息
     */
    @ExceptionHandler(LoginException.class)
    public Result<String> loginException(LoginException e) {
        return Results.result(e.getStatus(), e.getData());
    }
}
