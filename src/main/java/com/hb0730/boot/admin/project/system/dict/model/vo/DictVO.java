package com.hb0730.boot.admin.project.system.dict.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 数据字典
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode
@ToString
public class DictVO implements Serializable {
    private String type;
    private List<DictEntryVO> entry;

    @Data
    @ToString
    @EqualsAndHashCode
    public static class DictEntryVO implements Serializable {
        private String label;
        private String value;
    }
}
