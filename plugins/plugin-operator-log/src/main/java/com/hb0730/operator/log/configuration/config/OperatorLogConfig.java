package com.hb0730.operator.log.configuration.config;

import com.hb0730.base.utils.ConfigUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作日志配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/10
 */
@Data
@ConfigurationProperties(prefix = "zoom.operator.log")
public class OperatorLogConfig {

    /**
     * 错误信息长度
     */
    private Integer errorMessageLength;

    /**
     * userAgent 长度
     */
    private Integer userAgentLength;

    /**
     * 忽略记录的字段
     */
    private List<String> ignore = new ArrayList<>();

    /**
     * 需要脱敏的字段
     */
    private List<String> desensitize = new ArrayList<>();

    public OperatorLogConfig() {
        this.errorMessageLength = 255;
        this.userAgentLength = 128;
    }

    public void setIgnore(List<String> ignore) {
        this.ignore = ConfigUtil.parseStringList(ignore);
    }

    public void setDesensitize(List<String> desensitize) {
        this.desensitize = ConfigUtil.parseStringList(desensitize);
    }
}
