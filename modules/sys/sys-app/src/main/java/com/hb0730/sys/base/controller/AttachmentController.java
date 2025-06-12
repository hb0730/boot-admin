package com.hb0730.sys.base.controller;


import com.hb0730.base.R;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.oss.core.OssStorage;
import com.hb0730.oss.util.OssUtil;
import com.hb0730.security.SecurityUtils;
import com.hb0730.sys.base.model.vo.FilePresignedUrlRespVO;
import com.hb0730.sys.system.convert.SysAttachmentConvert;
import com.hb0730.sys.system.model.entity.SysAttachment;
import com.hb0730.sys.system.model.request.attachment.SysAttachmentCreateRequest;
import com.hb0730.sys.system.model.vo.SysAttachmentVO;
import com.hb0730.sys.system.service.SysAttachmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 通用控制器
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/12/20
 */
@RestController
@RequestMapping("/sys/common")
@Tag(name = "基础设施: 通用控制器")
@RequiredArgsConstructor
public class AttachmentController {
    private final OssStorage ossStorage;
    private final SysAttachmentService sysAttachmentService;
    @Autowired(required = false)
    private SysAttachmentConvert sysAttachmentConvert;

    /**
     * 上传文件
     *
     * @param request 请求
     * @param file    文件
     * @return {@link R <String>}
     */
    @PostMapping("/upload_file")
    @Operation(summary = "上传文件", description = "模式一: 后端上传")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "path", description = "路径", required = false)
    })
    public R<SysAttachmentVO> upload(HttpServletRequest request,
                                     @RequestPart MultipartFile file,
                                     @RequestParam(required = false) String path) throws IOException {
        Optional<String> username = SecurityUtils.getLoginUsername();
        if (username.isEmpty()) {
            return R.NG("请登录");
        }
        String _path;
        if (StrUtil.isBlank(path)) {
            _path = username.get();
        } else {
            path = OssUtil.normalize(path);
            _path = username.get() + "/" + path;
        }
        SysAttachment sysAttachment = uploadFile(file, _path, ossStorage);
        sysAttachmentService.save(sysAttachment);
        SysAttachmentVO res = sysAttachmentConvert.toVo(sysAttachment);
        return R.OK(res);
    }

    /**
     * 生成文件预签名地址
     *
     * @param filename    文件名
     * @param contentType 文件类型
     * @return {@link R<?>}
     */
    @GetMapping("/presigned_url")
    @Operation(summary = "生成文件预签名地址", description = "模式二: 前端上传")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "filename", description = "文件名,支持path格式,2023/10/xxx.png",
                    required =
                            true)
            , @io.swagger.v3.oas.annotations.Parameter(name = "contentType", description = "文件类型", required = false)
    })
    public R<FilePresignedUrlRespVO> presignedUrl(@RequestParam String filename,
                                                  @RequestParam(required = false) String contentType) {
        Optional<String> loginUsername = SecurityUtils.getLoginUsername();
        if (loginUsername.isEmpty()) {
            return R.NG("请登录");
        }
        Map<String, String> params = new HashMap<>();
        if (contentType != null) {
            params.put("Content-Type", contentType);
        }
        String objectKey = OssUtil.getObjectKey(OssUtil.renameObjectKey(filename, loginUsername.get()));
        OssStorage.PresignedUrl presignedUrl = ossStorage.getPresignedUrl(objectKey, params);
        if (presignedUrl == null) {
            return R.NG("获取预签名地址失败");
        }
        FilePresignedUrlRespVO respVO = new FilePresignedUrlRespVO();
        respVO.setObjectKey(objectKey);
        respVO.setAccessUrl(presignedUrl.getAccessUrl());
        respVO.setUploadUrl(presignedUrl.getPresignedUrl());
        return R.OK(respVO);
    }


    /**
     * 上传文件
     *
     * @param request 请求
     * @return {@link R<String>}
     */
    @PostMapping("/create_file")
    @Operation(summary = "创建文件", description = "模式二：前端上传文件：配合 presigned-url 接口，记录上传了上传的文件信息")
    public R<String> create(@RequestBody SysAttachmentCreateRequest request) {
        sysAttachmentService.create(request);
        return R.OK();
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @param path 路径
     * @return 附件信息
     */
    private SysAttachment uploadFile(MultipartFile file, String path, OssStorage storage) throws IOException {
        String objectKey = OssUtil.getObjectKey(OssUtil.renameObjectKey(file.getOriginalFilename(), path));
        long size = file.getSize();
        String contentType = file.getContentType();
        String accessUrl = storage.upload(objectKey, size, contentType, file.getInputStream());

        SysAttachment attachment = new SysAttachment();
        attachment.setDisplayName(objectKey);
        attachment.setPermalink(accessUrl);
        attachment.setSize(size);
        attachment.setMediaType(contentType);
        return attachment;
    }

}
