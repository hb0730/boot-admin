package com.hb0730.boot.admin.project.img.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.boot.admin.commons.domain.service.IBaseService;
import com.hb0730.boot.admin.oss.model.UploadResult;
import com.hb0730.boot.admin.project.img.model.entity.BaseImgEntity;
import com.hb0730.boot.admin.project.img.model.vo.BaseImgParams;
import com.hb0730.boot.admin.project.img.model.vo.BaseImgVO;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 图库  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-06-30
 */
public interface IBaseImgService extends IService<BaseImgEntity>, IBaseService<BaseImgParams, BaseImgEntity> {
    /**
     * 分页查询
     *
     * @param params 请求参数
     * @return 分页列表
     */
    Page<BaseImgVO> page(@NonNull BaseImgParams params);

    /**
     * 列表查询
     *
     * @param params 请求参数
     * @return 列表
     */
    List<BaseImgVO> list(@NonNull BaseImgParams params);

    /**
     * 文件上传
     *
     * @param file 文件
     * @return 是否成功
     */
    @NonNull
    UploadResult upload(@NonNull MultipartFile file);
}
