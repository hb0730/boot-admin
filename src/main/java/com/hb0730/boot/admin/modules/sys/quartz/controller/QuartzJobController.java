package com.hb0730.boot.admin.modules.sys.quartz.controller;

import com.hb0730.boot.admin.base.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.quartz.CronExpression;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Quartz定时任务Controller
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/12
 */
@RestController
@RequestMapping("/quartz/job")
@Tag(name = "定时任务")
@RequiredArgsConstructor
public class QuartzJobController {

    /**
     * 表达式是否正确
     *
     * @param request .
     * @param cron    .
     * @return .
     */
    @GetMapping("/check/cron")
    @Operation(summary = "表达式是否正确")
    public R<Boolean> checkCron(HttpServletRequest request, String cron) {
        boolean success = CronExpression.isValidExpression(cron);
        return success ? R.OK() : R.NG("表达式错误");
    }
}
