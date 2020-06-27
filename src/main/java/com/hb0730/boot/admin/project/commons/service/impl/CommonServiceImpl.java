package com.hb0730.boot.admin.project.commons.service.impl;

import com.hb0730.boot.admin.oss.handler.OssHandler;
import com.hb0730.boot.admin.oss.model.UploadResult;
import com.hb0730.boot.admin.project.commons.service.ICommonService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 通用service
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Service
public class CommonServiceImpl implements ICommonService {
    private final OssHandler ossHandler;

    public CommonServiceImpl(OssHandler ossHandler) {
        this.ossHandler = ossHandler;
    }

    @Override
    public UploadResult upload(MultipartFile file) {
        return ossHandler.upload(file);
    }
}
