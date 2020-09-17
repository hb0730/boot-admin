package com.hb0730.boot.admin.exceptions;

import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import com.hb0730.boot.admin.domain.result.R;
import com.hb0730.boot.admin.domain.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;

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
     * 业务异常信息
     *
     * @param e LoginException
     * @return 响应信息
     */
    @ExceptionHandler(AbstractException.class)
    public Result<String> loginException(AbstractException e) {
        return R.result(e.getStatus(), e.getData());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> validExceptionHandler(MethodArgumentNotValidException e) {
        LOGGER.error(e.getMessage(), e);
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return R.result(ResponseStatusEnum.PARAMS_REQUIRED_IS_NULL, message);
    }

    /**
     * 用户不存在
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public Result<String> handlerUsernameNotFoundException(UsernameNotFoundException e) {
        LOGGER.error(e.getMessage(), e);
        return R.result(ResponseStatusEnum.USER_NAME_NOT_FONT, "用户不存在,请确认账户是否正确");
    }

    /**
     * 路径不存在
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<String> handlerNoFoundException(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return R.result(ResponseStatusEnum.URL_NOT_FOUND, "路径不存在，请检查路径是否正确");
    }


    /**
     * 基础异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result<String> handlerBaseException(BusinessException e) {
        LOGGER.error(e.getMessage(), e);
        return R.fail(e.getMessage());
    }

}
