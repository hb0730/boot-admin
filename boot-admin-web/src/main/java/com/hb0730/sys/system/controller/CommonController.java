package com.hb0730.sys.system.controller;

import cn.hutool.core.util.IdUtil;
import com.hb0730.base.R;
import com.hb0730.base.utils.AesEncryptUtil;
import com.hb0730.base.utils.S3Util;
import com.hb0730.base.utils.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/19
 */
@RestController
@RequestMapping("/common")
@Tag(name = "系统：通用接口")
@Slf4j
public class CommonController {


    @GetMapping("/decoder")
    @Operation(summary = "解码")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "data", description = "加密数据", required = true)
    })
    public R<String> decoder(@RequestParam(name = "data") String code) {
        try {
            String res = AesEncryptUtil.decrypt(code);
            return R.OK(res);
        } catch (Exception e) {
            log.error("解码失败", e);
            return R.NG("解码失败");
        }
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return .
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json;charset=UTF-8")
    @Operation(summary = "上传文件")
    @ApiResponse(responseCode = "200", description = "上传成功,返回文件访问地址")
    public R<String> uploadFile(@RequestPart("file") MultipartFile file) {
        try {
            String fullName = file.getOriginalFilename();
            // get suffix
            String suffix = null;
            if (StrUtil.isNotBlank(fullName)) {
                suffix = fullName.substring(fullName.lastIndexOf("."));
            }
            String uuid = IdUtil.fastSimpleUUID();
            String filename = String.format("%s.%s", uuid, suffix);
            // upload oss
            String url = S3Util.uploadFile(filename, file.getInputStream());
            return R.OK(url);
        } catch (Exception e) {
            log.error("上传失败", e);
            return R.NG("上传失败,请稍后重试");
        }
    }

    /**
     * 多文件上传
     *
     * @param files 文件
     * @return .
     */
    @PostMapping(value = "/uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json;" +
            "charset=UTF-8")
    @Operation(summary = "多文件上传")
    @ApiResponse(responseCode = "200", description = "上传成功")
    public R<String> uploadFiles(@RequestPart("files") MultipartFile[] files) {
        return R.NG("暂不支持多文件上传");
    }
}
