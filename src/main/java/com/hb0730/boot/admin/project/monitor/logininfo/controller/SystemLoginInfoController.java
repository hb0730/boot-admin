package com.hb0730.boot.admin.project.monitor.logininfo.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hb0730.boot.admin.commons.utils.PageInfoUtil;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.project.monitor.logininfo.model.entity.SystemLoginInfoEntity;
import com.hb0730.boot.admin.project.monitor.logininfo.model.vo.LoginfoParams;
import com.hb0730.boot.admin.project.monitor.logininfo.model.vo.SystemLoginfoVO;
import com.hb0730.boot.admin.project.monitor.logininfo.service.ISystemLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.hb0730.boot.admin.commons.constant.RequestMappingNameConstants.REQUEST_LOGININFO;

/**
 * <p>
 * 系统登录日志  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-01
 */
@RestController
@RequestMapping(REQUEST_LOGININFO)
public class SystemLoginInfoController extends BaseController {
    @Autowired
    private ISystemLoginInfoService systemLoginInfoService;

    /**
     * <p>
     * 获取日志
     * </p>
     *
     * @param page     页数
     * @param pageSize 数量
     * @param params   过滤参数
     * @return 分页后的日志
     */
    @PostMapping("/all/page/{page}/{pageSize}")
    public Result getPageAll(@PathVariable Integer page, @PathVariable Integer pageSize, @RequestBody LoginfoParams params) {
        PageHelper.startPage(page, pageSize);
        PageInfo<SystemLoginInfoEntity> pageInfo = new PageInfo<>(systemLoginInfoService.list());
        PageInfo<SystemLoginfoVO> info = PageInfoUtil.toBean(pageInfo, SystemLoginfoVO.class);
        return ResponseResult.resultSuccess(info);
    }

    /**
     * 删除
     *
     * @param ids id
     * @return 是否成功
     */
    @PostMapping("/delete")
    public Result deleteLogin(@RequestBody List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return ResponseResult.resultFall("请选择");
        }
        systemLoginInfoService.removeByIds(ids);
        return ResponseResult.resultSuccess("删除成功");
    }

    /**
     * <p>
     * 清空
     * </p>
     *
     * @return 是否成功
     */
    @GetMapping("/clean")
    public Result cleanLog() {
        List<SystemLoginInfoEntity> list = systemLoginInfoService.list();
        if (!CollectionUtils.isEmpty(list)) {
            Set<Long> ids = list.parallelStream().map(SystemLoginInfoEntity::getId).collect(Collectors.toSet());
            systemLoginInfoService.removeByIds(ids);
        }
        return ResponseResult.resultSuccess("删除成功");
    }
}

