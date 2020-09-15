package com.hb0730.boot.admin.project.notice.mail.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 测试模板
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode
@ToString
public class MailTestDTO implements Serializable {
    /**
     * 收件者
     */
    @NotBlank(message = "收件人不为空")
    private String to;
    /**
     * 主题
     */
    @NotBlank(message = "主题不为空")
    private String subject;
    /**
     * 内容
     */
    @NotBlank(message = "内容不为空")
    private String content;
}
