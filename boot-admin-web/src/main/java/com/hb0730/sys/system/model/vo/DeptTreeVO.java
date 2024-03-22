package com.hb0730.sys.system.model.vo;

import com.hb0730.base.utils.TreeUtil;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 部门树
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/18
 */
@Getter
@Setter
public class DeptTreeVO implements TreeUtil.Node<DeptTreeVO, Integer>, Serializable {
    @Schema(description = "id")
    private Integer id;
    /**
     * 父类id
     */
    @Schema(description = "父类id")
    private Integer parentId;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String name;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;
    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean enabled;
    /**
     * 子类
     */
    @ArraySchema(schema = @Schema(description = "子类", implementation = Object.class, allOf = DeptTreeVO.class))
    private List<DeptTreeVO> children;
}
