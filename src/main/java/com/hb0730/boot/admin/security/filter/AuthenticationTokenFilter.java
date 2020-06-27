package com.hb0730.boot.admin.security.filter;

import com.hb0730.boot.admin.commons.utils.spring.SecurityUtils;
import com.hb0730.boot.admin.configuration.properties.BootAdminProperties;
import com.hb0730.boot.admin.security.handle.TokenHandlers;
import com.hb0730.boot.admin.security.model.LoginUser;
import com.hb0730.boot.admin.security.service.ITokenService;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * <p>
 * token过滤器 验证token有效性
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {
    /**
     * token service
     */
    private final TokenHandlers tokenHandlers;
    /**
     * tokenpeizhi
     */
    private final BootAdminProperties properties;

    public AuthenticationTokenFilter(TokenHandlers tokenHandlers, BootAdminProperties properties) {
        this.tokenHandlers = tokenHandlers;
        this.properties = properties;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ITokenService service = tokenHandlers.getImpl(properties.getTokenType());
        LoginUser loginUser = service.getLoginUser(request);
        if (!Objects.isNull(loginUser) && StringUtils.isEmpty(SecurityUtils.getAuthentication())) {
            service.verifyAccessToken(loginUser);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
