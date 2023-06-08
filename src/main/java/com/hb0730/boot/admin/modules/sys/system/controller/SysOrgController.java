package com.hb0730.boot.admin.modules.sys.system.controller;

import cn.hutool.core.util.StrUtil;
import com.hb0730.boot.admin.base.R;
import com.hb0730.boot.admin.data.domain.BasePage;
import com.hb0730.boot.admin.modules.sys.system.model.query.OrganizationQuery;
import com.hb0730.boot.admin.modules.sys.system.model.vo.OrganizationTree;
import com.hb0730.boot.admin.modules.sys.system.model.vo.OrganizationVO;
import com.hb0730.boot.admin.modules.sys.system.service.SysOrgService;
import com.hb0730.boot.admin.security.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 机构Controller
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/3
 */
@RestController
@RequestMapping("/sys/org")
@Tag(name = "系统：机构")
@RequiredArgsConstructor
public class SysOrgController {
    private final SysOrgService sysOrgService;

    @GetMapping("/query/page")
    @Operation(summary = "分页查询")
    @PreAuthorize("hasAuthority('org:query')")
    public R<BasePage<OrganizationVO>> getListPage(HttpServletRequest request,@ParameterObject OrganizationQuery query) {
        BasePage<OrganizationVO> page = sysOrgService.queryPage(query);
        return R.OK(page);
    }

    @GetMapping("/query/tree")
    @Operation(summary = "树形结构")
    @PreAuthorize("hasAuthority('org:query:tree')")
    public R<List<OrganizationTree>> getTree(HttpServletRequest request,@ParameterObject OrganizationQuery query) {
        List<OrganizationTree> res = sysOrgService.queryTree(query);
        return R.OK(res);
    }

    @GetMapping("/query/list")
    @Operation(summary = "列表")
    public R<List<OrganizationVO>> getList(HttpServletRequest request,@ParameterObject OrganizationQuery query) {
        List<OrganizationVO> res = sysOrgService.queryList(query);
        return R.OK(res);
    }

    @PostMapping("/save")
    @Operation(summary = "保存机构")
    @PreAuthorize("hasAuthority('org:save')")
    public R<OrganizationVO> save(HttpServletRequest request, @RequestBody OrganizationVO vo) {
        String parentId = vo.getParentId();
        if (StrUtil.isBlank(parentId)) {
            return R.NG("不允许添加根节点");
        }
        vo.setCreatedBy(SecurityUtil.getCurrentUsername());
        vo.setCreated(LocalDateTime.now());
        vo.setIsSystem(0);
        return sysOrgService.save(vo);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "更新机构")
    @PreAuthorize("hasAuthority('org:update')")
    public R<OrganizationVO> update(HttpServletRequest request, @PathVariable("id") String id,
                                    @RequestBody OrganizationVO vo) {
        vo.setModifiedBy(SecurityUtil.getCurrentUsername());
        vo.setModified(LocalDateTime.now());
        return sysOrgService.updateById(id, vo);
    }

    @DeleteMapping("/del")
    @Operation(summary = "删除机构")
    @PreAuthorize("hasAuthority('org:del')")
    public R<String> del(HttpServletRequest request, @RequestBody List<String> ids) {
        return sysOrgService.batchDel(ids);
    }


}
