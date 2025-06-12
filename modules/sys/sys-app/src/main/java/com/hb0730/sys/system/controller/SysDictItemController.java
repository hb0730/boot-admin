package com.hb0730.sys.system.controller;

import com.hb0730.base.R;
import com.hb0730.core.data.Page;
import com.hb0730.operator.log.core.annotation.OperatorLog;
import com.hb0730.sys.system.define.operator.DictItemOperatorType;
import com.hb0730.sys.system.model.request.dict.item.SysDictItemCreateRequest;
import com.hb0730.sys.system.model.request.dict.item.SysDictItemQueryRequest;
import com.hb0730.sys.system.model.vo.SysDictItemVO;
import com.hb0730.sys.system.service.SysDictItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.hb0730.base.pool.ConstPool.DICT_ITEMS_KEY;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/12
 */
@RestController
@RequestMapping("/sys/dict/item")
@Tag(name = "基础设施: 数据字典项")
@Slf4j
@RequiredArgsConstructor
public class SysDictItemController {
    private final SysDictItemService service;

    @GetMapping("/page")
    @Operation(summary = "分页查询数据字典项")
    public R<Page<SysDictItemVO>> page(SysDictItemQueryRequest request) {
        return R.OK(service.page(request));
    }

    @GetMapping("/list")
    @Operation(summary = "查询数据字典项")
    public R<List<SysDictItemVO>> list(SysDictItemQueryRequest request) {
        return R.OK(service.list(request));
    }

    @PostMapping("/save")
    @Operation(summary = "保存数据字典项")
    @OperatorLog(DictItemOperatorType.ADD)
    @CacheEvict(value = DICT_ITEMS_KEY, allEntries = true)
    @PreAuthorize("hasAuthority('sys:dict:item:add')")
    public R<String> save(@RequestBody SysDictItemCreateRequest request) {
        service.create(request);
        return R.OK();
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "更新数据字典项")
    @OperatorLog(DictItemOperatorType.UPDATE)
    @CacheEvict(value = DICT_ITEMS_KEY, allEntries = true)
    @PreAuthorize("hasAuthority('sys:dict:item:update')")
    public R<String> update(@PathVariable String id, @RequestBody SysDictItemCreateRequest request) {
        service.updateById(id, request);
        return R.OK();
    }

    @DeleteMapping("/del")
    @Operation(summary = "删除数据字典项")
    @OperatorLog(DictItemOperatorType.DELETE)
    @PreAuthorize("hasAuthority('sys:dict:item:delete')")
    @CacheEvict(value = DICT_ITEMS_KEY, allEntries = true)
    public R<String> delete(String id) {
        service.deleteById(id);
        return R.OK();
    }

}
