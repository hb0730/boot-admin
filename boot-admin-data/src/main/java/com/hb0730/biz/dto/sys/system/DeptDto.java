package com.hb0730.biz.dto.sys.system;

import com.hb0730.base.enums.ValueEnum;
import com.hb0730.base.enums.sys.DeptTypeEnums;
import com.hb0730.base.utils.TreeUtil;
import com.hb0730.data.dto.BaseDto;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/18
 */
@Getter
@Setter
@Accessors(chain = true)
public class DeptDto extends BaseDto implements TreeUtil.Node<DeptDto, Integer> {
    @Schema(description = "id")
    private Integer id;

    @Override
    public Integer getId() {
        return id;
    }

    /**
     * 父类id
     */
    @Schema(description = "父类id")
    private Integer parentId;

    @Override
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 部门编码
     */
    @Schema(description = "部门编码")
    @NotBlank(message = "部门编码不能为空")
    private String code;
    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    @NotBlank(message = "部门名称不能为空")
    private String name;

    /**
     * 部门类型 1 公司 2 分公司 3 部门
     */
    @Schema(description = "部门类型 1 公司 2 分公司 3 部门")
    private Integer type;

    /**
     * 部门类型名称
     */
    @Schema(description = "部门类型名称")
    public String getTypeName() {
        if (null == type) {
            return null;
        }
        DeptTypeEnums typeEnums = ValueEnum.valueToEnum(DeptTypeEnums.class, type);
        return null == typeEnums ? null : typeEnums.getDesc();
    }

    /**
     * 负责人
     */
    @Schema(description = "负责人")
    private String principal;
    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    private String phone;
    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;
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
    @ArraySchema(schema = @Schema(description = "子类", implementation = Object.class, allOf = DeptDto.class))
    private List<DeptDto> children;

    @Override
    public void setChildren(List<DeptDto> children) {
        this.children = children;
    }

    @Override
    public List<DeptDto> getChildren() {
        return children;
    }
}
