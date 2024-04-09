package com.hb0730.webscoket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/2
 */
@Configuration
@EnableWebSocket
public class WebsocketConfiguration {

    @Bean
    public WebSocketConfigurer webSocketConfigurer() {
        return null;
    }
}
