package com.hb0730.boot.admin.project.notice.mail.controller;

import com.hb0730.boot.admin.annotation.ClassDescribe;
import com.hb0730.boot.admin.annotation.Log;
import com.hb0730.boot.admin.commons.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.domain.result.R;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.listener.option.OptionListener;
import com.hb0730.boot.admin.project.notice.mail.model.dto.MailDTO;
import com.hb0730.boot.admin.project.notice.mail.model.dto.MailTestDTO;
import com.hb0730.boot.admin.project.notice.mail.service.IMailService;
import com.hb0730.commons.mail.spring.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * mail controller
 *
 * @author bing_huang
 */
@RestController()
@RequestMapping("/api/v3/message/mail")
@Validated
@RequiredArgsConstructor
@ClassDescribe("邮件服务")
public class MailController {
    private final IMailService service;
    private final OptionListener listener;

    /**
     * 详情
     *
     * @return mail
     */
    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATOR','mail:info')")
    public Result<MailDTO> info() {
        MailDTO info = service.info();
        return R.success(info);
    }

    /**
     * 保存
     *
     * @param dto mail
     * @return 是否成功
     */
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATOR','mail:save')")
    @Log(value = "保存", paramsName = {"dto"}, businessType = BusinessTypeEnum.UPDATE)
    public Result<String> save(@RequestBody MailDTO dto) {
        service.save(dto);
        return R.success("保存成功");
    }

    /**
     * 测试发送
     *
     * @param dto 测试发送信息
     * @return 是否成功
     */
    @PostMapping("/test")
    public Result<String> test(@RequestBody MailTestDTO dto) {
        MailService service = listener.getMailService();
        service.sendTextMail(dto.getTo(), dto.getSubject(), dto.getContent());
        return R.success("测试成功");
    }
}
