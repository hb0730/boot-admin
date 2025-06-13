package com.hb0730.sys.system.controller;

import com.hb0730.base.R;
import com.hb0730.core.data.Page;
import com.hb0730.security.SecurityUtils;
import com.hb0730.sys.system.model.request.attachment.SysAttachmentQueryRequest;
import com.hb0730.sys.system.model.vo.SysAttachmentVO;
import com.hb0730.sys.system.service.SysAttachmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/12/20
 */
@RestController
@RequestMapping("/sys/attachment/list")
@Tag(name = "系统: 附件管理")
@RequiredArgsConstructor
public class SysAttachmentController {
    private final SysAttachmentService sysAttachmentService;

    /**
     * 分页查询
     *
     * @param request 请求
     * @return {@link R <Page< SysAttachmentVO >>}
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询", description = "分页查询")
    public R<Page<SysAttachmentVO>> page(SysAttachmentQueryRequest request) {
        Optional<String> loginUsername = SecurityUtils.getLoginUsername();
        loginUsername.ifPresent(request::setCreatedBy);
        Page<SysAttachmentVO> page = sysAttachmentService.page(request);
        return R.OK(page);
    }


    /**
     * 查询
     *
     * @param request 请求
     * @return {@link R<List<SysAttachmentVO>>}
     */
    @GetMapping("/list")
    @Operation(summary = "查询", description = "查询")
    public R<List<SysAttachmentVO>> list(SysAttachmentQueryRequest request) {
        SecurityUtils.getLoginUsername().ifPresent(request::setCreatedBy);
        List<SysAttachmentVO> list = sysAttachmentService.list(request);
        return R.OK(list);
    }

    /**
     * 删除
     *
     * @param id id
     * @return {@link R<String>}
     */
    @DeleteMapping("{id}")
    @Operation(summary = "删除", description = "删除")
    public R<String> delete(@PathVariable String id) {
        sysAttachmentService.deleteById(id);
        return R.OK();
    }
}
