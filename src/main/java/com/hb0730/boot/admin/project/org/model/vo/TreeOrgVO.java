package com.hb0730.boot.admin.project.org.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 树形vo
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TreeOrgVO extends SystemOrgVO {

    /**
     * 子集
     */
    private List<TreeOrgVO> children;
}
