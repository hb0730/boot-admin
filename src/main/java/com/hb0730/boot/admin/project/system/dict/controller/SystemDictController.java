package com.hb0730.boot.admin.project.system.dict.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.commons.annotation.Log;
import com.hb0730.boot.admin.commons.constant.ModuleName;
import com.hb0730.boot.admin.commons.constant.RequestMappingNameConstants;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.commons.constant.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.commons.domain.controller.AbstractBaseController;
import com.hb0730.boot.admin.commons.domain.result.ResponseResult;
import com.hb0730.boot.admin.commons.domain.result.Result;
import com.hb0730.boot.admin.exception.BaseException;
import com.hb0730.boot.admin.project.system.dict.model.entity.SystemDictEntity;
import com.hb0730.boot.admin.project.system.dict.model.vo.DictParams;
import com.hb0730.boot.admin.project.system.dict.model.vo.SystemDictVO;
import com.hb0730.boot.admin.project.system.dict.service.ISystemDictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 数据字段类型  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-30
 */
@RestController
@RequestMapping(RequestMappingNameConstants.REQUEST_DICT)
public class SystemDictController extends AbstractBaseController<Long, SystemDictVO, DictParams, SystemDictEntity> {
    private final ISystemDictService systemDictService;

    public SystemDictController(ISystemDictService systemDictService) {
        super(systemDictService);
        this.systemDictService = systemDictService;
    }

    /**
     * 数据字典(管理)
     *
     * @param params 过滤菜蔬
     * @return 是否成功
     */
    @PostMapping("/page/all")
    @PreAuthorize("hasAnyAuthority('dict:query','ROLE_ADMINISTRATOR','ROLE_DICT_ADMIN')")
    public Result<Page<SystemDictVO>> allPageList(@RequestBody DictParams params) {
        Page<SystemDictVO> page = systemDictService.page(SystemConstants.PARENT_ID, params);
        return ResponseResult.resultSuccess(page);
    }

    /**
     * <p>
     * 根据父id获取字典子集
     * </p>
     *
     * @param parentId 父id
     * @param params   过滤参数
     * @return 字典信息
     */
    @PostMapping("/page/all/data/{parentId}")
    @PreAuthorize("hasAnyAuthority('dict:data:query','ROLE_ADMINISTRATOR','ROLE_DICT_ADMIN')")
    public Result<Page<SystemDictVO>> allPageDataList(@PathVariable Long parentId, @RequestBody DictParams params) {
        Page<SystemDictVO> page = systemDictService.page(parentId, params);
        return ResponseResult.resultSuccess(page);
    }

    /**
     * <p>
     * 保存
     * </p>
     *
     * @param vo 字典参数
     * @return 是否成功
     */
    @Override
    @PreAuthorize("hasAnyAuthority('dict:save','ROLE_ADMINISTRATOR','ROLE_DICT_ADMIN')")
    @Log(paramsName = "vo", module = ModuleName.DICT, title = "新增", businessType = BusinessTypeEnum.INSERT)
    public Result<String> save(@RequestBody SystemDictVO vo) {
        if (Objects.isNull(vo.getParentId())) {
            vo.setParentId(SystemConstants.PARENT_ID);
        }
        verify(vo);
        return super.save(vo);
    }

    /**
     * <p>
     * 根据id修改
     * </p>
     *
     * @param id id
     * @param vo 参数
     * @return 是否成
     */
    @Override
    @PreAuthorize("hasAnyAuthority('dict:update','ROLE_ADMINISTRATOR','ROLE_DICT_ADMIN')")
    @Log(paramsName = {"vo"}, module = ModuleName.DICT, title = "修改", businessType = BusinessTypeEnum.UPDATE)
    public Result<String> updateById(@PathVariable("id") Long id, @RequestBody SystemDictVO vo) {
        verify(vo);
        systemDictService.updateById(id, vo);
        return ResponseResult.resultSuccess("修改成功");
    }

    /**
     * <p>
     * 根据id删除
     * </P>
     *
     * @param id id
     * @return 是否成功
     */
    @Override
    @PreAuthorize("hasAnyAuthority('dict:delete','ROLE_ADMINISTRATOR','ROLE_DICT_ADMIN')")
    @Log(module = ModuleName.DICT, title = "删除", businessType = BusinessTypeEnum.DELETE)
    public Result<String> deleteById(@PathVariable("id") Long id) {
        return super.deleteById(id);
    }

    /**
     * 根据id批量删除
     *
     * @param ids id
     * @return 是否成功
     */
    @Override
    @PreAuthorize("hasAnyAuthority('dict:delete','ROLE_ADMINISTRATOR','ROLE_DICT_ADMIN')")
    @Log(module = ModuleName.DICT, title = "删除", businessType = BusinessTypeEnum.DELETE)
    public Result<String> deleteByIds(@RequestBody List<Long> ids) {
        return super.deleteByIds(ids);
    }

    /**
     * <p>
     * 将数据字典转换成map传给前端
     * </p>
     *
     * @return map格式的数据字典
     */
    @GetMapping("/map")
    public Result<Map<String, List<Map<String, Object>>>> getDictForMap() {
        Map<String, List<Map<String, Object>>> map = systemDictService.getDictForMap();
        return ResponseResult.resultSuccess(map);
    }

    /**
     * 校验
     *
     * @param vo 字典参数
     */
    private void verify(@NonNull SystemDictVO vo) {
        if (Objects.equals(vo.getParentId(), SystemConstants.PARENT_ID)) {
            if (StringUtils.isBlank(vo.getNumber())) {
                throw new BaseException("字典编码不为空");
            }
            if (Objects.isNull(vo.getId())) {
                QueryWrapper<SystemDictEntity> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq(SystemDictEntity.NUMBER, vo.getNumber());
                List<SystemDictEntity> entities = systemDictService.list(queryWrapper);
                if (!CollectionUtils.isEmpty(entities)) {
                    throw new BaseException("字典编码已存在");
                }
            }
            if (StringUtils.isBlank(vo.getName())) {
                throw new BaseException("字典名称不为空");
            }
        } else {
            if (StringUtils.isBlank(vo.getDictLabel())) {
                throw new BaseException("请输入数据项标签");
            }
            if (StringUtils.isBlank(vo.getDictValue())) {
                throw new BaseException("请输入数据项值");
            }
            if (StringUtils.isBlank(vo.getDictType())) {
                throw new BaseException("请输入数据项的类型");
            }
        }
    }
}

