package com.hb0730.boot.admin.project.commons.service.impl;

import com.hb0730.boot.admin.file.enums.AttachmentTypeEnum;
import com.hb0730.boot.admin.file.handler.FileHandlers;
import com.hb0730.boot.admin.file.model.UploadResult;
import com.hb0730.boot.admin.project.commons.service.ICommonService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Service
public class CommonServiceImpl implements ICommonService {
    private FileHandlers fileHandlers;

    public CommonServiceImpl(FileHandlers fileHandlers) {
        this.fileHandlers = fileHandlers;
    }

    @Override
    public UploadResult upload(MultipartFile file) {
        return fileHandlers.upload(file, AttachmentTypeEnum.LOCAL);
    }
}
