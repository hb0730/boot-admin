package com.hb0730.sys.quartz.controller;

import com.hb0730.base.R;
import com.hb0730.biz.dto.sys.quartz.QuartzJobDto;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.security.utils.SecurityUtil;
import com.hb0730.sys.bean.QuartzQuery;
import com.hb0730.sys.quartz.rpcservice.IQuartzRemoteService;
import com.hb0730.sys.service.QuartzJobRcpService;
import com.hb0730.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/21
 */
@RestController
@RequestMapping("/sys/quartz/job")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "系统: 定时任务管理")
public class QuartzJobController {
    private final IQuartzRemoteService quartzRemoteService;
    private final QuartzJobRcpService quartzJobRcpService;

    /**
     * 验证cron表达式
     *
     * @param cronExpression cron表达式
     * @return 是否成功
     */
    @GetMapping("/validate/cron")
    @Parameters({
            @Parameter(name = "cronExpression", description = "cron表达式", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    public R<String> validateCronExpression(String cronExpression) {
        return quartzRemoteService.validateCronExpression(cronExpression);
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    @GetMapping
    @Operation(summary = "分页查询")
    @PreAuthorize("hasAnyAuthority('sys:quartz:job:list','ROLE_ADMIN')")
    public R<JsfPage<QuartzJobDto>> page(QuartzQuery query) {
        JR<JsfPage<QuartzJobDto>> jr = quartzJobRcpService.page(query);
        return ResponseUtil.converter(jr);
    }

    /**
     * 保存
     *
     * @param dto 保存数据
     * @return 是否成功
     */
    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAnyAuthority('sys:quartz:job:save','ROLE_ADMIN')")
    public R<String> save(@Valid @RequestBody QuartzJobDto dto) {
        String username = SecurityUtil.getUsername();
        dto.setCreatedBy(username);
        dto.setCreated(new Date());
        return quartzRemoteService.add(dto);
    }


    /**
     * 更新
     *
     * @param dto 更新数据
     * @return 是否成功
     */
    @PutMapping
    @Operation(summary = "更新")
    @PreAuthorize("hasAnyAuthority('sys:quartz:job:update','ROLE_ADMIN')")
    public R<String> update(@Valid @RequestBody QuartzJobDto dto) {
        if (null == dto.getId()) {
            return R.NG("id不能为空");
        }
        String username = SecurityUtil.getUsername();
        dto.setCreated(new Date());
        dto.setCreatedBy(username);
        return quartzRemoteService.edit(dto);
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAnyAuthority('sys:quartz:job:delete','ROLE_ADMIN')")
    @Parameters({
            @Parameter(name = "id", description = "主键", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "integer"))
    })
    public R<String> delete(@RequestParam("id") Integer id) {
        return quartzRemoteService.delete(id);
    }

    /**
     * 执行
     *
     * @param id 主键
     * @return 是否成功
     */
    @PostMapping("/run")
    @Operation(summary = "执行")
    @PreAuthorize("hasAnyAuthority('sys:quartz:job:run','ROLE_ADMIN')")
    @Parameters({
            @Parameter(name = "id", description = "主键", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "integer"))
    })
    public R<String> execute(@RequestParam("id") Integer id) {
        return quartzRemoteService.executeJob(id);
    }

    /**
     * 暂停
     *
     * @param id 主键
     * @return 是否成功
     */
    @PutMapping("/pause")
    @Operation(summary = "暂停")
    @PreAuthorize("hasAnyAuthority('sys:quartz:job:pause','ROLE_ADMIN')")
    @Parameters({
            @Parameter(name = "id", description = "主键", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "integer"))
    })
    public R<String> pause(@RequestParam("id") Integer id) {
        return quartzRemoteService.pauseJob(id);
    }

    /**
     * 恢复
     *
     * @param id 主键
     * @return 是否成功
     */
    @PutMapping("/resume")
    @Operation(summary = "恢复")
    @PreAuthorize("hasAnyAuthority('sys:quartz:job:resume','ROLE_ADMIN')")
    @Parameters({
            @Parameter(name = "id", description = "主键", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "integer"))
    })
    public R<String> resume(@RequestParam("id") Integer id) {
        return quartzRemoteService.resumeJob(id);
    }

}
