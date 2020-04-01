package com.hb0730.boot.admin.project.system.post.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hb0730.boot.admin.commons.annotation.Log;
import com.hb0730.boot.admin.commons.constant.BusinessTypeEnum;
import com.hb0730.boot.admin.commons.constant.ModuleName;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.commons.utils.PageInfoUtil;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.project.system.post.model.entity.SystemPostEntity;
import com.hb0730.boot.admin.project.system.post.model.vo.PostParams;
import com.hb0730.boot.admin.project.system.post.model.vo.SystemPostVO;
import com.hb0730.boot.admin.project.system.post.service.ISystemPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.hb0730.boot.admin.commons.constant.RequestMappingNameConstants.REQUEST_POST;

/**
 * <p>
 * 系统岗位  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-28
 */
@RestController
@RequestMapping(REQUEST_POST)
public class SystemPostController extends BaseController {
    @Autowired
    private ISystemPostService systemPostService;

    /**
     * <p>
     * 保存
     * </p>
     *
     * @param vo 岗位
     * @return 是否成功
     */
    @PostMapping("/save")
    @Log(paramsName = {"vo"}, module = ModuleName.POST, title = "岗位保存", businessType = BusinessTypeEnum.INSERT)
    public Result save(@RequestBody SystemPostVO vo) {
        SystemPostEntity entity = BeanUtils.transformFrom(vo, SystemPostEntity.class);
        systemPostService.save(entity);
        return ResponseResult.resultSuccess("保存成功");
    }

    /**
     * <p>
     * 获取分页后的岗位
     * </p>
     *
     * @param page     页数
     * @param pageSize 数量
     * @param params   过滤参数
     * @return 分页后的岗位
     */
    @PostMapping("/all/{page}/{pageSize}")
    public Result getPostPage(@PathVariable Integer page, @PathVariable Integer pageSize, @RequestBody PostParams params) {
        PageHelper.startPage(page, pageSize);
        QueryWrapper<SystemPostEntity> queryWrapper = new QueryWrapper<>();
        List<SystemPostEntity> entities = systemPostService.list(queryWrapper);
        PageInfo<SystemPostEntity> pageInfo = new PageInfo<>(entities);
        PageInfo<SystemPostVO> info = PageInfoUtil.toBean(pageInfo, SystemPostVO.class);
        return ResponseResult.resultSuccess(info);
    }

    /**
     * <p>
     * 获取岗位
     * </p>
     *
     * @param params 过滤条件
     * @return 岗位信息
     */
    @PostMapping("/all")
    public Result getPost(@RequestBody PostParams params) {
        QueryWrapper<SystemPostEntity> queryWrapper = new QueryWrapper<>();
        if (!Objects.isNull(params)) {
            if (!Objects.isNull(params.getIsAll()) && !Objects.equals(SystemConstants.IS_ALL, params.getIsAll())) {
                queryWrapper.eq(SystemPostEntity.IS_ENABLED, SystemConstants.USE);
            }
        }
        List<SystemPostEntity> entities = systemPostService.list(queryWrapper);
        List<SystemPostVO> vos = BeanUtils.transformFromInBatch(entities, SystemPostVO.class);
        return ResponseResult.resultSuccess(vos);
    }

    /**
     * <p>
     * 更新岗位
     * </p>
     *
     * @param id 岗位id
     * @param vo 岗位信息
     * @return 是否成功
     */
    @PostMapping("/update/{id}")
    @Log(paramsName = {"vo"}, module = ModuleName.POST, title = "岗位修改", businessType = BusinessTypeEnum.UPDATE)
    public Result updateById(@PathVariable Long id, @RequestBody SystemPostVO vo) {
        SystemPostEntity entity = systemPostService.getById(id);
        BeanUtils.updateProperties(vo, entity);
        systemPostService.updateById(entity);
        return ResponseResult.resultSuccess("修改成功");
    }

    /**
     * 删除
     *
     * @param id 岗位id
     * @return 是否成功
     */
    @GetMapping("/delete/{id}")
    @Log(module = ModuleName.POST, title = "岗位删除", businessType = BusinessTypeEnum.DELETE)
    public Result deleteById(@PathVariable Long id) {
        systemPostService.deleteById(id);
        return ResponseResult.resultSuccess("修改成功");
    }
}

