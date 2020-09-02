package com.hb0730.boot.admin.project.system.dept.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 树形组织
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class TreeDeptDTO extends DeptDTO {
    /**
     * 子集
     */
    private List<TreeDeptDTO> children;
}
