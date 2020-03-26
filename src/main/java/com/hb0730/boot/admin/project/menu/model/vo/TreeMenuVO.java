package com.hb0730.boot.admin.project.menu.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TreeMenuVO extends SystemMenuVO {
    /**
     * 子集
     */
    private List<TreeMenuVO> children;
}
