package com.hb0730.boot.admin.project.img.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.commons.constant.enums.AttachmentTypeEnum;
import com.hb0730.boot.admin.commons.domain.service.SuperBaseService;
import com.hb0730.boot.admin.commons.utils.PageUtils;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.commons.utils.file.FileUtils;
import com.hb0730.boot.admin.commons.utils.spring.SpringUtils;
import com.hb0730.boot.admin.configuration.properties.BootAdminProperties;
import com.hb0730.boot.admin.exception.file.FileAlreadyExistsException;
import com.hb0730.boot.admin.oss.handler.OssHandler;
import com.hb0730.boot.admin.oss.model.UploadResult;
import com.hb0730.boot.admin.project.img.mapper.IBaseImgMapper;
import com.hb0730.boot.admin.project.img.model.entity.BaseImgEntity;
import com.hb0730.boot.admin.project.img.model.vo.BaseImgParams;
import com.hb0730.boot.admin.project.img.model.vo.BaseImgVO;
import com.hb0730.boot.admin.project.img.service.IBaseImgService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 图库  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-06-30
 */
@Service
@AllArgsConstructor
public class BaseImgServiceImpl extends SuperBaseService<BaseImgParams, BaseImgVO, IBaseImgMapper, BaseImgEntity> implements IBaseImgService {
    private final OssHandler handler;

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseImgServiceImpl.class);

    @Override
    public Page<BaseImgVO> page(@NonNull BaseImgParams params) {
        QueryWrapper<BaseImgEntity> query = query(params);
        Page<BaseImgEntity> page = QueryWrapperUtils.getPage(params);
        page = super.page(page, query);
        return PageUtils.toBean(page, BaseImgVO.class);
    }

    @Override
    public List<BaseImgVO> list(@NonNull BaseImgParams params) {
        QueryWrapper<BaseImgEntity> query = query(params);
        List<BaseImgEntity> list = super.list(query);
        return BeanUtils.transformFromInBatch(list, BaseImgVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {
        BaseImgEntity entity = super.getById(id);
        handler.delete(entity.getFileKey());
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        List<BaseImgEntity> entities = super.listByIds(idList);
        Set<String> keys = entities.parallelStream().map(BaseImgEntity::getFileKey).collect(Collectors.toSet());
        keys.forEach(handler::delete);
        return super.removeByIds(idList);
    }

    @Override
    @NonNull
    public UploadResult upload(@NonNull MultipartFile file) {
        Assert.notNull(file, "文件不为空");
        AttachmentTypeEnum type = SpringUtils.getBean(BootAdminProperties.class).getAttachmentType();
        LOGGER.debug("开始上传, 类型: [{}], file:[{}]", type, file.getOriginalFilename());
        UploadResult result = handler.upload(file);
        LOGGER.debug("上传结果:[{}]", result);
        BaseImgEntity entity = new BaseImgEntity();
        entity.setName(result.getFilename());
        entity.setPath(FileUtils.changeFileSeparatorToUrlSeparator(result.getFilePath()));
        entity.setFileKey(result.getKey());
        entity.setThumbPath(result.getThumbPath());
        entity.setMediaType(result.getMediaType().toString());
        entity.setSuffix(result.getSuffix());
        entity.setWidth(result.getWidth());
        entity.setHeight(result.getHeight());
        entity.setSize(result.getSize());
        entity.setType(type.getValue());
        LOGGER.debug("save upload result :[{}]", entity);
        // 保存
        save(entity);
        return result;
    }

    @NonNull
    @Override
    public QueryWrapper<BaseImgEntity> query(@NonNull BaseImgParams params) {
        Assert.notNull(params, "请求参数不为空");
        QueryWrapper<BaseImgEntity> query = QueryWrapperUtils.getQuery(params);
        return query;
    }

    @Override
    public boolean save(BaseImgEntity entity) {
        Assert.notNull(entity, "entity 不为空");
        pathMustNotExist(entity);
        return super.save(entity);
    }

    /**
     * 检查路径是否存在
     *
     * @param entity 图片entity
     */
    private void pathMustNotExist(@NonNull BaseImgEntity entity) {
        Assert.notNull(entity, "entity 不为空");
        QueryWrapper<BaseImgEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseImgEntity.PATH, entity.getPath());
        int count = super.count(queryWrapper);
        if (count > 0) {
            throw new FileAlreadyExistsException("图片路径{}已存在", entity.getPath());
        }
    }
}
