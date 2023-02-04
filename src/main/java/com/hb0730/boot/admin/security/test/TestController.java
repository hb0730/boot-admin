package com.hb0730.boot.admin.security.test;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/4
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/a1")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String test1() {
        return "角色认证成功";
    }

    @GetMapping("/a2")
    @PreAuthorize("@permission.hashPermission('test:query')")
    public String test2() {
        return "自定义权限认证成功";
    }
}
