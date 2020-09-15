package com.hb0730.boot.admin.project.notice.mail.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * mail dto
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode
@ToString
public class MailDTO implements Serializable {
    /**
     * 发送主机
     */
    @NotBlank(message = "SMTP不为空")
    private String host;

    /**
     * 协议
     */
    @NotBlank(message = "协议不为空")
    private String protocol;

    /**
     * ssl 端口
     */
    @NotEmpty(message = "端口不为空")
    private Integer sslPort;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不为空")
    private String password;

    /**
     * 发件人
     */
    @NotBlank(message = "发件人不为空")
    private String fromName;

    /**
     * 是否启用
     */
    @Range(min = 0, max = 1, message = "启用只能为0或者1")
    private Integer enabled;
}
