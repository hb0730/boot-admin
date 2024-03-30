package com.hb0730.rpc.basic.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Data
@EqualsAndHashCode
@ToString
public class OrgSmallDto implements Serializable {
    /**
     * 机构ID
     */
    private String id;
    /**
     * 机构名称
     */
    private String name;
}
