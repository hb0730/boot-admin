package com.hb0730.boot.admin.project.commons.service;

import com.hb0730.boot.admin.file.model.UploadResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface ICommonService {
    /**
     * 文件上传
     * 图片
     *
     * @param file 文件信息
     * @return 上传信息
     */
    UploadResult upload(MultipartFile file);
}
