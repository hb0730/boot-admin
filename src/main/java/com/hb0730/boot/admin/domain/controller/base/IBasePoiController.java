package com.hb0730.boot.admin.domain.controller.base;

import com.hb0730.boot.admin.domain.model.query.BaseParams;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.exceptions.ExportExceptions;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * excel 操作
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IBasePoiController<PARAMS extends BaseParams> {

    /**
     * 导出
     *
     * @param response 响应
     * @param params   请求参数,用于导出过滤
     * @throws ExportExceptions io异常
     */
    default void export(HttpServletResponse response, PARAMS params) throws ExportExceptions {
    }

    /**
     * 导出
     *
     * @param file 文件
     * @throws IOException io异常
     */
    default Result<String> upload(MultipartFile file) throws IOException {
        return null;
    }

    /**
     * 批量导入
     *
     * @param files 文件
     * @throws IOException io异常
     */
    default Result<String> uploads(MultipartFile[] files) throws IOException {
        return null;
    }
}
