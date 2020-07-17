package com.hb0730.boot.admin.project.img.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.domain.model.domain.BusinessDomain;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
@TableName("t_base_img")
public class BaseImgEntity extends BusinessDomain {

    private static final long serialVersionUID=1L;

    /**
     * 备注
     */
    @TableField("description")
    private String description;

    /**
     * id
     */
    @TableField("id")
    private Long id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 路径
     */
    @TableField("path")
    private String path;

    /**
     * 密钥
     */
    @TableField("file_key")
    private String fileKey;

    /**
     * 缩略图访问路径
     */
    @TableField("thumb_path")
    private String thumbPath;

    /**
     * 类型
     */
    @TableField("media_type")
    private String mediaType;

    /**
     * 后缀
     */
    @TableField("suffix")
    private String suffix;

    /**
     * 宽
     */
    @TableField("width")
    private Integer width;

    /**
     * 高
     */
    @TableField("height")
    private Integer height;

    /**
     * 大小
     */
    @TableField("size")
    private Long size;

    /**
     * 上传类型
     */
    @TableField("type")
    private Integer type;


    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String PATH = "path";

    public static final String FILE_KEY = "file_key";

    public static final String THUMB_PATH = "thumb_path";

    public static final String MEDIA_TYPE = "media_type";

    public static final String SUFFIX = "suffix";

    public static final String WIDTH = "width";

    public static final String HEIGHT = "height";

    public static final String SIZE = "size";

    public static final String TYPE = "type";

}
