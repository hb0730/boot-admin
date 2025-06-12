package com.hb0730.spring.security.core.handler;

import com.hb0730.base.R;
import com.hb0730.base.utils.JsonUtil;
import com.hb0730.spring.security.core.service.SecurityHolder;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.Optional;

/**
 * 权限不足处理器
 * <p>
 * {@code @PreAuthorize("hasAuthority('xxx') } 返回 false 会进入此处理器
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ForbiddenAccessDeniedHandler implements AccessDeniedHandler {
    private final SecurityHolder securityHolder;

    @Override
    @ExceptionHandler(AccessDeniedException.class)
    public void handle(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Optional<String> loginUserId = securityHolder.getLoginUserId();
        log.warn("AccessDeniedHandlerImpl-handle-forbidden {} {}", loginUserId.orElse("null"), request.getRequestURI());
        R<Object> jr = R.NG(403, "无操作权限");
        String res = JsonUtil.DEFAULT.toJson(jr);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(res);
    }
}
