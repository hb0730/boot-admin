package com.hb0730.boot.admin.exception;

import com.hb0730.boot.admin.domain.result.CodeStatusEnum;
import com.hb0730.boot.admin.domain.result.ResponseResult;
import com.hb0730.boot.admin.domain.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;

/**
 * <p>
 * 全局异常处理
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * <p>
     * 用户未找到
     * </p>
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public Result handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseResult.result(CodeStatusEnum.USERNAME_NOTFOUND, e.getMessage());
    }

    /**
     * <p>
     * 密码不匹配
     * </p>
     */
    @ExceptionHandler(BadCredentialsException.class)
    public Result handlerBadCredentialsException(BadCredentialsException e) {
        log.error(e.getMessage(), e);
        return ResponseResult.result(CodeStatusEnum.FORBIDDEN, "用户名或者密码错误");
    }

    /**
     * 基础异常
     */
    @ExceptionHandler(BaseException.class)
    public Result handlerBaseException(BaseException e) {
        return ResponseResult.resultFall(e.getMessage());
    }

    /**
     * <p>
     * 访问受限，授权过期
     * </p>
     */
    @ExceptionHandler(AccessDeniedException.class)
    public Result handlerAccessDeniedException(AccessDeniedException e) {
        log.error(e.getMessage(), e);
        return ResponseResult.result(CodeStatusEnum.UNAUTHORIZED, "没有权限，请联系管理员授权");
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public Result validatedBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return ResponseResult.resultFall(message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validExceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return ResponseResult.resultFall(message);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseResult.result(CodeStatusEnum.NOT_FOUND, "路径不存在，请检查路径是否正确");
    }

}
