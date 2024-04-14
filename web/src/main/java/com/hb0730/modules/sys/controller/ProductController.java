package com.hb0730.modules.sys.controller;

import com.hb0730.base.R;
import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.sys.system.domain.ProductDto;
import com.hb0730.rpc.sys.system.domain.query.ProductQuery;
import com.hb0730.rpc.sys.system.service.ProductRpcService;
import com.hb0730.security.util.SecurityUtil;
import com.hb0730.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
import java.util.List;

/**
 * 管理端-产品管理
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/27
 */
@Tag(name = "管理端：产品管理")
@RestController
@RequestMapping("/sys/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRpcService productRpcService;

    /**
     * 根据code查询
     *
     * @param code code
     * @param id   需要排除的id
     * @return .
     */
    @Operation(summary = "根据code查询")
    @GetMapping("/existsByCode")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "code", description = "code", required = true),
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "需要排除的id", required = false)
    })
    public R<Boolean> existsByCode(@RequestParam String code, @RequestParam(required = false) Long id) {
        JR<Boolean> jr = productRpcService.existsByCode(code, id);
        return ResponseUtil.converter(jr);
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return .
     */
    @Operation(summary = "分页查询")
    @GetMapping
    @PreAuthorize("hasAuthority('tenant:product:query')")
    public R<JsfPage<ProductDto>> page(ProductQuery query) {
        JR<JsfPage<ProductDto>> page = productRpcService.page(query);
        return ResponseUtil.converter(page);
    }

    /**
     * 列表查询
     *
     * @param query 查询条件
     * @return .
     */
    @Operation(summary = "列表查询")
    @GetMapping("/list")
    public R<List<ProductDto>> list(ProductQuery query) {
        JR<List<ProductDto>> list = productRpcService.list(query);
        return ResponseUtil.converter(list);
    }

    /**
     * 保存
     *
     * @param dto 产品信息
     * @return .
     */
    @Operation(summary = "保存")
    @PostMapping
    @PreAuthorize("hasAuthority('tenant:product:add')")
    public R<String> save(@RequestBody @Valid ProductDto dto) {
        String username = SecurityUtil.getUsername();
        dto.setCreated(new Date());
        dto.setCreatedBy(username);
        JR<String> jr = productRpcService.save(dto);
        return ResponseUtil.converter(jr);
    }

    /**
     * 更新
     *
     * @param dto 产品信息
     * @return .
     */
    @Operation(summary = "更新")
    @PutMapping
    @PreAuthorize("hasAuthority('tenant:product:update')")
    public R<String> update(@RequestBody @Valid ProductDto dto) {
        if (dto.getId() == null) {
            return R.NG("id不能为空");
        }
        String username = SecurityUtil.getUsername();
        dto.setModified(new Date());
        dto.setModifiedBy(username);
        JR<String> jr = productRpcService.updateById(dto);
        return ResponseUtil.converter(jr);
    }

    /**
     * 删除
     *
     * @param id 产品ID
     * @return .
     */
    @Operation(summary = "删除")
    @DeleteMapping
    @PreAuthorize("hasAuthority('tenant:product:delete')")
    public R<String> delete(Long id) {
        JR<String> jr = productRpcService.deleteById(id);
        return ResponseUtil.converter(jr);
    }

    /**
     * 授权
     *
     * @param id      产品ID
     * @param menuIds 菜单ID集合
     * @return .
     */
    @Operation(summary = "授权")
    @PutMapping("/grant")
    @PreAuthorize("hasAuthority('tenant:product:grant')")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "产品ID", required = true),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "菜单ID集合", required = true)
    public R<String> grant(@RequestParam(value = "id") Long id, @RequestBody List<Long> menuIds) {
        JR<String> jr = productRpcService.grant(id, menuIds);
        return ResponseUtil.converter(jr);
    }
}
