package com.hb0730.boot.admin.domain.controller.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.aspectj.FieldInfoAspectj;
import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import com.hb0730.boot.admin.domain.model.query.BaseParams;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.result.R;
import com.hb0730.boot.admin.domain.service.ISuperBaseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * 基础controller之查询
 * <pre>
 * 1. 实现分页查询{@link #page(BaseParams)}
 * 2. 实现列表查询{@link #list(BaseParams)}
 * 3. 实现共用的权限{@link PreAuthorize}{@link com.hb0730.boot.admin.annotation.PreAuth}{@link com.hb0730.boot.admin.security.service.PermissionService}
 * 4. 实现日志的记录{@link com.hb0730.boot.admin.annotation.Log}{@link com.hb0730.boot.admin.annotation.ClassDescribe} {@link com.hb0730.boot.admin.aspectj.LogAspectj}
 * </pre>
 *
 * @param <ENTITY> 实体类型
 * @param <ID>     id类型
 * @param <PARAMS> 请求参数类型
 * @param <DTO>    显示层对象类型
 * @author bing_huang
 * @since 3.0.0
 */
public interface IBaseQueryController<ID extends Serializable,
        DTO extends BaseDTO,
        PARAMS extends BaseParams, ENTITY extends BaseDomain> extends IBaseController<ENTITY> {
    /**
     * 分页查询
     *
     * @param params 请求参数
     * @return 分页列表
     */

    @PostMapping("/list/page" )
    @SuppressWarnings({"unchecked"})
    @PreAuthorize("@bootAdmin.hasAnyAuthority(this,'ROLE_ADMINISTRATOR','query')" )
    default Result<Object> page(@Validated @RequestBody PARAMS params) {
        ISuperBaseService<ID, PARAMS, DTO, ENTITY> service = getBaseService();
        if (null != service) {
            Page<DTO> page = service.page(params);
            return R.success(page);
        }
        return R.result(ResponseStatusEnum.PARAMS_REQUIRED_IS_NULL, "service is null" );
    }

    /**
     * 列表查询
     *
     * @param params 请求参数
     * @return 列表
     */
    @PostMapping("/list" )
    @SuppressWarnings({"unchecked"})
    default Result<Object> list(@Validated @RequestBody PARAMS params) {
        ISuperBaseService<ID, PARAMS, DTO, ENTITY> service = getBaseService();
        if (null != service) {
            List<DTO> list = service.list(params);
            return R.success(list);
        }
        return R.result(ResponseStatusEnum.PARAMS_REQUIRED_IS_NULL, "service is null" );
    }

    /**
     * 获取表字段
     *
     * @return 表字段
     * @since 3.0.0
     */
    @GetMapping("/columns" )
    default Result<Map<String, String>> columns() {
        Map<String, String> fieldInfo = FieldInfoAspectj.getFieldInfo(getDtoClass());
        return R.success(fieldInfo);
    }

    /**
     * 获取dto具体的类型
     *
     * @return {@link Class}类型的DTO
     * @since 3.0.0
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    default Class<DTO> getDtoClass() {
        return (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
}
