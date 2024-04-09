package com.hb0730.modules.basic.controller;

import com.hb0730.base.R;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.rpc.basic.domain.BasNoticeDto;
import com.hb0730.rpc.basic.domain.query.BasNoticeQuery;
import com.hb0730.rpc.basic.service.BasNoticeRpcService;
import com.hb0730.security.domain.dto.UserInfoDto;
import com.hb0730.security.domain.dto.UserOrgInfoDto;
import com.hb0730.security.util.SecurityUtil;
import com.hb0730.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/2
 */
@RestController
@RequestMapping("/bas/notice")
@Tag(name = "履约端：公告管理")
@RequiredArgsConstructor
public class BasNoticeController {
    private final BasNoticeRpcService basNoticeRpcService;

    /**
     * 未读统计
     *
     * @return .
     */
    @GetMapping("/unread/count")
    @Operation(summary = "未读统计")
    public R<Integer> countNoRead() {
        UserInfoDto currentUser = SecurityUtil.getCurrentUser();
        if (currentUser == null) {
            return R.OK();
        }
        JR<Integer> jr = basNoticeRpcService.countUnRead(currentUser.getId());
        return ResponseUtil.converter(jr);
    }

    /**
     * 分页查询公告
     *
     * @param query .
     * @return .
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询公告")
    public R<JsfPage<BasNoticeDto>> page(BasNoticeQuery query) {
        UserInfoDto currentUser = SecurityUtil.getCurrentUser();
        if (currentUser == null) {
            return R.OK();
        }
        UserOrgInfoDto orgInfo = currentUser.getOrgInfo();
        if (orgInfo == null) {
            return R.OK();
        }
        String tenantId = orgInfo.getPath().split(",")[0];
        query.setOrgId(tenantId);
        query.setUserId(currentUser.getId());
        JR<JsfPage<BasNoticeDto>> jr = basNoticeRpcService.page(query);
        return ResponseUtil.converter(jr);
    }

    /**
     * 读公告
     *
     * @param noticeIds .
     * @return .
     */
    @PutMapping("/read")
    @Operation(summary = "读公告")
    public R<String> reads(@RequestBody List<String> noticeIds) {
        UserInfoDto currentUser = SecurityUtil.getCurrentUser();
        if (currentUser == null) {
            return R.OK();
        }
        JR<String> jr = basNoticeRpcService.read(currentUser.getId(), noticeIds);
        return ResponseUtil.converter(jr);
    }

}
