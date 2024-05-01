package com.hb0730.sys.domain.vo;

import com.hb0730.security.domain.vo.TreeVO;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Getter
@Setter
public class MenuTreeVO implements TreeVO<MenuTreeVO> {
    @Schema(description = "id")
    private String id;
    /**
     * 父类id
     */
    @Schema(description = "父类id")
    private String parentId;
    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String title;
    /**
     * 子类
     */
    @ArraySchema(schema = @Schema(description = "子类", implementation = Object.class, allOf = MenuTreeVO.class))
    private List<MenuTreeVO> children;
}