package com.hb0730.sys.system.controller;

import com.hb0730.base.R;
import com.hb0730.base.enums.sys.MenuTypeEnums;
import com.hb0730.sys.system.model.vo.SelectOptionVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/15
 */
@RestController
@RequestMapping("/sys/enums")
@Tag(name = "枚举管理")
public class EnumsController {
    /**
     * 菜单类型
     *
     * @return 菜单类型
     */
    @GetMapping("/menuType")
    @Operation(summary = "菜单类型")
    public R<List<SelectOptionVO>> menuType() {
        List<SelectOptionVO> res = Arrays.stream(MenuTypeEnums.values()).map(e -> {
            SelectOptionVO vo = new SelectOptionVO();
            vo.setLabel(e.getName());
            vo.setValue(e.getValue() + "");
            return vo;
        }).toList();
        return R.OK(res);
    }

    /**
     * 部门类型
     *
     * @return 部门类型
     */
    @GetMapping("/deptType")
    @Operation(summary = "部门类型")
    public R<List<SelectOptionVO>> deptType() {
        return R.OK(Arrays.stream(MenuTypeEnums.values()).map(e -> {
            SelectOptionVO vo = new SelectOptionVO();
            vo.setLabel(e.getName());
            vo.setValue(e.getValue() + "");
            return vo;
        }).toList());
    }
}
