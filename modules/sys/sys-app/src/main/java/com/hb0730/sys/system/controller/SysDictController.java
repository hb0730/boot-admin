package com.hb0730.sys.system.controller;

import com.hb0730.base.R;
import com.hb0730.cache.core.CacheUtil;
import com.hb0730.core.data.Option;
import com.hb0730.core.data.Page;
import com.hb0730.operator.log.core.annotation.OperatorLog;
import com.hb0730.sys.system.define.operator.DictItemOperatorType;
import com.hb0730.sys.system.model.request.dict.SysDictCreateRequest;
import com.hb0730.sys.system.model.request.dict.SysDictQueryRequest;
import com.hb0730.sys.system.model.vo.SysDictVO;
import com.hb0730.sys.system.service.SysDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
 * @date 2024/10/11
 */
@RestController
@RequestMapping("/sys/dict")
@Tag(name = "基础设施: 数据字典")
@Validated
@Slf4j
@RequiredArgsConstructor
public class SysDictController {
    private final SysDictService sysDictService;

    @Autowired
    private CacheUtil cacheUtil;


    /**
     * 是否存在编码
     *
     * @param code 编码
     * @param id   需要排除的id
     * @return 是否存在
     */
    @GetMapping("/has_code")
    @Operation(summary = "是否存在编码")
    public R<String> hasCode(@Parameter String code,
                             @Parameter(required = false) String id) {
        return sysDictService.hasCode(code, id);
    }

    @GetMapping("/page")
    @Operation(summary = "数据字典分页查询")
    public R<Page<SysDictVO>> page(SysDictQueryRequest query) {
        Page<SysDictVO> page = sysDictService.page(query);
        return R.OK(page);
    }

    @GetMapping("/list")
    @Operation(summary = "数据字典列表查询")
    public R<List<SysDictVO>> list(SysDictQueryRequest query) {
        List<SysDictVO> page = sysDictService.list(query);
        return R.OK(page);

    }

    /**
     * 保存
     *
     * @param request 数据字典
     * @return 是否成功
     */
    @PostMapping("/save")
    @Operation(summary = "数据字典保存")
    @OperatorLog(DictItemOperatorType.ADD)
    @PreAuthorize("hasAuthority('sys:dict:add')")
    public R<String> save(@RequestBody SysDictCreateRequest request) {
        sysDictService.create(request);
        return R.OK();
    }

    /**
     * 更新
     *
     * @param id      id
     * @param request 数据字典
     * @return 是否成功
     */
    @PutMapping("/update/{id}")
    @Operation(summary = "数据字典更新")
    @OperatorLog(DictItemOperatorType.UPDATE)
    @PreAuthorize("hasAuthority('sys:dict:update')")
    public R<String> update(@PathVariable String id, @RequestBody SysDictCreateRequest request) {
        sysDictService.updateById(id, request);
        return R.OK();
    }

    /**
     * 删除
     *
     * @param id id
     * @return 是否成功
     */
    @DeleteMapping("/del")
    @Operation(summary = "数据字典删除")
    @OperatorLog(DictItemOperatorType.DELETE)
    @PreAuthorize("hasAuthority('sys:dict:delete')")
    @CacheEvict(value = DICT_ITEMS_KEY, allEntries = true)
    public R<String> del(@Parameter String id) {
        sysDictService.deleteById(id);
        return R.OK();
    }


    /**
     * 加载数据字典项
     *
     * @param dictCode 字典编码
     * @return 数据字典项
     */
    @GetMapping("/load_items")
    @Operation(summary = "加载数据字典项")
    public R<List<Option>> loadItems(String dictCode) {
        List<Option> items = sysDictService.loadItems(dictCode);
        return R.OK(items);
    }

    /**
     * 刷新缓存
     *
     * @return 是否成功
     */
    @PutMapping("/refresh_cache")
    @Operation(summary = "刷新缓存")
    public R<String> refreshCache() {
        cacheUtil.delScan(DICT_ITEMS_KEY);
        return R.OK();
    }


}
