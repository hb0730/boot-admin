package com.hb0730.boot.admin.security.conroller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/1")
    public String test() {
        return "认证成功" + SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @RequestMapping("/2")
    @PreAuthorize("hasAnyAuthority('admin:query')")
    public String test2() {
        return "权限认证成功" + SecurityContextHolder.getContext().getAuthentication();
    }
    @RequestMapping("/3")
    @PreAuthorize("hasAnyAuthority('admin:test')")
    public String test3() {
        return "权限认证成功" + SecurityContextHolder.getContext().getAuthentication();
    }

    @RequestMapping("/4")
    @PreAuthorize("hasAnyRole('admin')")
    public String test4(){
        return "权限认证成功" + SecurityContextHolder.getContext().getAuthentication();
    }
}
