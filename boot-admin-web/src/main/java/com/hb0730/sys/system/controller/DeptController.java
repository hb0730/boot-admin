package com.hb0730.sys.system.controller;

import com.hb0730.base.R;
import com.hb0730.base.utils.TreeUtil;
import com.hb0730.biz.dto.sys.system.DeptDto;
import com.hb0730.commons.JR;
import com.hb0730.security.utils.SecurityUtil;
import com.hb0730.sys.bean.DeptQuery;
import com.hb0730.sys.service.DeptRpcService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/18
 */
@RestController
@RequestMapping("/sys/dept")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "系统: 部门管理")
public class DeptController {
    private final DeptRpcService deptRpcService;


    /**
     * code是否存在
     *
     * @param code code
     * @param id   id 需要排除的id
     * @return .
     */
    @GetMapping("/existsCode")
    @Operation(summary = "code是否存在")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "code", description = "部门code", required = true),
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "需要排除的ID", required = false)
    })
    public R<Boolean> existsByCodeAndIdNot(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "id", required = false) Integer id) {
        JR<Boolean> jr = deptRpcService.existsByCodeAndIdNot(code, id);
        if (!jr.isSuccess()) {
            return R.NG(jr.getMessage());
        }
        return R.OK(jr.getResult());
    }

    /**
     * 树
     *
     * @param query .
     * @return .
     */
    @GetMapping()
    @Operation(summary = "部门树")
    public R<List<DeptDto>> tree(DeptQuery query) {
        JR<List<DeptDto>> jr = deptRpcService.list(query);
        if (!jr.isSuccess()) {
            return R.NG(jr.getMessage());
        }
        List<DeptDto> data = jr.getResult();
        List<DeptDto> tree = TreeUtil.buildTree(data);
        return R.OK(tree);
    }

    /**
     * 保存
     *
     * @param dto .
     * @return .
     */
    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAnyAuthority('sys:dept:save','ROLE_ADMIN')")
    public R<String> save(@Validated @RequestBody DeptDto dto) {
        String username = SecurityUtil.getUsername();
        dto.setCreatedBy(username);
        dto.setCreated(new java.util.Date());
        JR<String> jr = deptRpcService.save(dto);
        if (!jr.isSuccess()) {
            return R.NG(jr.getMessage());
        }
        return R.OK(jr.getResult());
    }

    /**
     * 修改
     *
     * @param dto .
     * @return .
     */
    @PutMapping
    @Operation(summary = "修改")
    @PreAuthorize("hasAnyAuthority('sys:dept:update','ROLE_ADMIN')")
    public R<String> update(@Validated @RequestBody DeptDto dto) {
        Integer id = dto.getId();
        if (id == null) {
            return R.NG("id不能为空");
        }
        if (id.equals(dto.getParentId())) {
            return R.NG("上级部门不能是自己");
        }
        String username = SecurityUtil.getUsername();
        dto.setModifyBy(username);
        dto.setModified(new java.util.Date());
        JR<String> jr = deptRpcService.updateById(dto);
        if (!jr.isSuccess()) {
            return R.NG(jr.getMessage());
        }
        return R.OK(jr.getResult());
    }

    /**
     * 删除
     *
     * @param id .
     * @return .
     */
    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAnyAuthority('sys:dept:delete','ROLE_ADMIN')")
    public R<String> delete(@RequestParam("id") Integer id) {
        JR<String> jr = deptRpcService.deleteById(id);
        if (!jr.isSuccess()) {
            return R.NG(jr.getMessage());
        }
        return R.OK(jr.getResult());
    }


}
