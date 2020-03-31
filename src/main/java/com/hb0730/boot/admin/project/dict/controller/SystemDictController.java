package com.hb0730.boot.admin.project.dict.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.hb0730.boot.admin.commons.constant.RequestMappingNameConstants;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.commons.utils.BeanUtils;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.commons.web.exception.BaseException;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.project.dict.model.entity.SystemDictEntity;
import com.hb0730.boot.admin.project.dict.model.vo.DictParams;
import com.hb0730.boot.admin.project.dict.model.vo.SystemDictVO;
import com.hb0730.boot.admin.project.dict.service.ISystemDictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SystemDictController extends BaseController {
    @Autowired
    private ISystemDictService systemDictService;

    /**
     * <p>
     * 数据字典(管理)
     * </p>
     *
     * @param page     分数
     * @param pageSize 数量
     * @param params   过滤菜蔬
     * @return 是否成功
     */
    @PostMapping("/page/all/{page}/{pageSize}")
    @PreAuthorize("hasAnyAuthority('dict:query','ROLE_ADMIN','ROLE_DICT')")
    public Result allPageList(@PathVariable Integer page, @PathVariable Integer pageSize, @RequestBody DictParams params) {
        PageInfo<SystemDictVO> info = systemDictService.getPageDict(SystemConstants.PARENT_ID, page, pageSize, params);
        return ResponseResult.resultSuccess(info);
    }

    /**
     * <p>
     * 根据父id获取字典子集
     * </p>
     *
     * @param parentId 父id
     * @param page     页数
     * @param pageSize 数量
     * @param params   过滤参数
     * @return 字典信息
     */
    @PostMapping("/page/all/data/{parentId}/{page}/{pageSize}")
    @PreAuthorize("hasAnyAuthority('dict:data:query','ROLE_ADMIN','ROLE_DICT')")
    public Result allPageDataList(@PathVariable Long parentId, @PathVariable Integer page, @PathVariable Integer pageSize, @RequestBody DictParams params) {
        PageInfo<SystemDictVO> info = systemDictService.getPageDict(parentId, page, pageSize, params);
        return ResponseResult.resultSuccess(info);
    }

    /**
     * <p>
     * 保存
     * </p>
     *
     * @param vo 字典参数
     * @return 是否成功
     */
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('dict:save','ROLE_ADMIN','ROLE_DICT')")
    public Result save(@RequestBody SystemDictVO vo) {
        verify(vo);
        SystemDictEntity entity = BeanUtils.transformFrom(vo, SystemDictEntity.class);
        systemDictService.save(entity);
        return ResponseResult.resultSuccess("保存成功");
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
    @PostMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('dict:update','ROLE_ADMIN','ROLE_DICT')")
    public Result updateById(@PathVariable Long id, @RequestBody SystemDictVO vo) {
        verify(vo);
        systemDictService.updateById(id, vo);
        return ResponseResult.resultSuccess("保存成功");
    }

    /**
     * <p>
     * 根据id删除
     * </P>
     *
     * @param id id
     * @return 是否成功
     */
    @GetMapping("/delete/id/{id}")
    @PreAuthorize("hasAnyAuthority('dict:delete','ROLE_ADMIN','ROLE_DICT')")
    public Result deleteById(@PathVariable Long id) {
        systemDictService.removeById(id);
        return ResponseResult.resultSuccess("删除成功");
    }

    /**
     * <p>
     * 将数据字典转换成map传给前端
     * </p>
     *
     * @return map格式的数据字典
     */
    @GetMapping("/map")
    public Result getDictForMap() {
        Map<String, List> map = systemDictService.getDictForMap();
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

