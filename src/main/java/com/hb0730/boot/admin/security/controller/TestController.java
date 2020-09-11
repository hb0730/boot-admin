package com.hb0730.boot.admin.security.controller;

import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author bing_huang
 * @since 3.0.0
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class TestController {
    @GetMapping("/test/ada")
    public Result<String> test() {
        return R.success("测试");
    }
}
