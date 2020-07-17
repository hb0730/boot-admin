package com.hb0730.boot.admin.project.img.model.vo;

import com.hb0730.boot.admin.utils.convert.InputConverter;
import com.hb0730.boot.admin.domain.model.web.BusinessVO;
import com.hb0730.boot.admin.project.img.model.entity.BaseImgEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 图库
 * </p>
 *
 * @author bing_huang
 * @since 2020-06-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseImgVO extends BusinessVO implements InputConverter<BaseImgEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    private String description;

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private String path;

    /**
     * 密钥
     */
    private String fileKey;

    /**
     * 缩略图访问路径
     */
    private String thumbPath;

    /**
     * 类型
     */
    private String mediaType;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 宽
     */
    private Integer width;

    /**
     * 高
     */
    private Integer height;

    /**
     * 大小
     */
    private Long size;

    /**
     * 上传类型
     */
    private Integer type;
}
