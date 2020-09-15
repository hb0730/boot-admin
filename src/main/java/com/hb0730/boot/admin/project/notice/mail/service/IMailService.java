package com.hb0730.boot.admin.project.notice.mail.service;

import com.hb0730.boot.admin.project.notice.mail.model.dto.MailDTO;
import com.hb0730.boot.admin.project.notice.mail.model.dto.MailTestDTO;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

/**
 * 邮箱服务
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IMailService {

    /**
     * 保存
     *
     * @param dto mail
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    boolean save(@NonNull MailDTO dto);

    /**
     * 详情
     *
     * @return mail
     */
    MailDTO info();

    /**
     * 测试
     *
     * @param dto 测试信息
     */
    void test(@NonNull MailTestDTO dto);
}
