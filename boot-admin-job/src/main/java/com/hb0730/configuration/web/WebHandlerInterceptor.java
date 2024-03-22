package com.hb0730.configuration.web;

import cn.hutool.crypto.digest.DigestUtil;
import com.hb0730.conf.rpc.RpcRemoteProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.hb0730.base.constant.RpcConstant.HEADER_REQSEQ_KEY;
import static com.hb0730.base.constant.RpcConstant.HEADER_TIMESTAMP_KEY;
import static com.hb0730.base.constant.RpcConstant.HEADER_TOKEN_KEY;
import static com.hb0730.base.constant.RpcConstant.HEADER_USER_KEY;

/**
 * 拦截器
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/6
 */
@Slf4j
@RequiredArgsConstructor
public class WebHandlerInterceptor implements HandlerInterceptor {
    private final RpcRemoteProperties rpcRemoteProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //请求标识用请求头
        String reqSeq = request.getHeader(HEADER_REQSEQ_KEY);
        String reqUser = request.getHeader(HEADER_USER_KEY);
        //安全认证用请求头
        String token = request.getHeader(HEADER_TOKEN_KEY);
        String stamp = request.getHeader(HEADER_TIMESTAMP_KEY);
        log.debug("~~~Job访问令牌请求头：{}--{}AT{}", reqSeq, token, stamp);

        if (StringUtils.isBlank(token) || StringUtils.isBlank(stamp)) {
            return false;
        }
        RpcRemoteProperties.Job job = rpcRemoteProperties.getJob();

        String secret = job.getSecret();
        return authentication(token, secret, stamp);
    }

    /**
     * 认证
     *
     * @return true
     */
    private boolean authentication(String token, String secret, String timestamp) {
        return token.equalsIgnoreCase(DigestUtil.md5Hex(secret + timestamp + timestamp + secret));
    }
}
