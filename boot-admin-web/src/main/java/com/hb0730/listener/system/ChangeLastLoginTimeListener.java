package com.hb0730.listener.system;

import com.hb0730.base.utils.StrUtil;
import com.hb0730.commons.JR;
import com.hb0730.security.event.LoginSuccessEvent;
import com.hb0730.security.utils.JwtUtil;
import com.hb0730.sys.service.UserRpcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/29
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class ChangeLastLoginTimeListener{
    private final UserRpcService userRpcService;

    @Async
    @org.springframework.context.event.EventListener
    public void onApplicationEvent(LoginSuccessEvent event) {
        log.info("【更新最后登录时间】 LoginSuccessEvent 事件监听器执行...");
        String token = event.getToken();
        if (StrUtil.isBlank(token)) {
            log.info("【更新最后登录时间】 token 为空");
            return;
        }
        String username = JwtUtil.getUsername(token);
        JR<String> jr = userRpcService.changeLastLoginTime(username);
        log.info("【更新最后登录时间】 结果: {}", jr);

        log.info("【更新最后登录时间】 LoginSuccessEvent 事件监听器执行结束...");
    }

}
