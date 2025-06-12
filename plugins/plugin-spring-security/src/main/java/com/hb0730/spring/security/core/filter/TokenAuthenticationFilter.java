package com.hb0730.spring.security.core.filter;

import com.hb0730.base.R;
import com.hb0730.base.meta.UserContext;
import com.hb0730.base.utils.ServletUtil;
import com.hb0730.spring.security.core.domain.IUserInfo;
import com.hb0730.spring.security.core.service.SecurityConfigService;
import com.hb0730.spring.security.core.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

/**
 * 认证过滤器
 * 验证 token 有效后将其加入上下文
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final SecurityConfigService securityConfigService;

    public TokenAuthenticationFilter(UserService userService, SecurityConfigService securityConfigService) {
        this.userService = userService;
        this.securityConfigService = securityConfigService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            // 获取请求头 token
            Optional<String> tokenOption = securityConfigService.obtainAuthorization(request);
            if (tokenOption.isPresent()) {
                // 通过 token 获取用户信息
                IUserInfo userInfo = userService.getUserByToken(tokenOption.get());
                if (userInfo != null) {
                    // 设置用户信息
                    setLoginUser(userInfo, request);
                }
            }
        } catch (Exception e) {
            log.error("TokenAuthenticationFilter.doFilterInternal parser error", e);
            R<Object> res = R.error(500, "系统异常");
            ServletUtil.writeJson(response, res);
            return;
        } finally {
            // 清理当前用户信息
            UserContext.clear();
        }

        chain.doFilter(request, response);
    }


    /**
     * 设置当前用户
     *
     * @param loginUser 登录用户
     * @param request   请求
     */
    public void setLoginUser(IUserInfo loginUser, HttpServletRequest request) {
        // 创建 authentication
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser, null,
                loginUser.getAuthorities());
        if (request != null) {
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        }
        // 设置上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 设置当前用户
        UserContext.setCurrentUserName(loginUser.getUsername());
    }
}
