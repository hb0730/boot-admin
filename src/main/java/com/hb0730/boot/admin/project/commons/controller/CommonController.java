package com.hb0730.boot.admin.project.commons.controller;

import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.oss.model.UploadResult;
import com.hb0730.boot.admin.project.commons.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.hb0730.boot.admin.commons.constant.SystemConstants.FILE_SEPARATOR;

/**
 * <p>
 * 通用控制器
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@RestController
public class CommonController {
    @Autowired
    private ICommonService commonService;

    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    public Result uploadFile(@RequestPart("file") MultipartFile file) throws Exception {
        UploadResult upload = commonService.upload(file);
        upload.setFilePath(upload.getFilePath().replace(FILE_SEPARATOR,"/"));
        return ResponseResult.resultSuccess(upload);
    }
}
