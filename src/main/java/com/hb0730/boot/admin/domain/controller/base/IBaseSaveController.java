package com.hb0730.boot.admin.domain.controller.base;

import cn.hutool.extra.validation.ValidationUtil;
import com.hb0730.boot.admin.annotation.Log;
import com.hb0730.boot.admin.commons.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import com.hb0730.boot.admin.commons.utils.ValidatorUtils;
import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import com.hb0730.boot.admin.domain.result.R;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.service.ISuperBaseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 基础controller之新增
 * <pre>
 * 1. 实现新增{@link #save(BaseDTO)}
 * 2. 通过{@link ValidationUtil#validate(Object, Class[])}实现对参数的校验
 * 3. 实现共用的权限{@link PreAuthorize}{@link com.hb0730.boot.admin.annotation.PreAuth}{@link com.hb0730.boot.admin.security.service.PermissionService}
 * 4. 实现日志的记录{@link com.hb0730.boot.admin.annotation.Log}{@link com.hb0730.boot.admin.annotation.ClassDescribe} {@link com.hb0730.boot.admin.aspectj.LogAspectj}
 * </pre>
 *
 * @param <ENTITY> 实体类型
 * @param <DTO>    显示层对象类型
 * @author bing_huang
 * @since 3.0.0
 */
public interface IBaseSaveController<DTO extends BaseDTO, ENTITY extends BaseDomain> extends IBaseController<ENTITY> {

    /**
     * 保存
     *
     * @param dto 参数
     * @return 是否成功
     */
    @PostMapping("/save")
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Log(value = "保存", paramsName = {"dto"}, businessType = BusinessTypeEnum.INSERT)
    @PreAuthorize("@permissionHandler.hasAnyAuthority(this,'ROLE_ADMINISTRATOR','save')")
    default Result<String> save(@RequestBody @Validated DTO dto) {
        ValidatorUtils.validate(dto);
        ISuperBaseService baseService = getBaseService();
        if (null != baseService) {
            baseService.save(dto);
            return R.success("保存成功");
        }
        return R.result(ResponseStatusEnum.PARAMS_REQUIRED_IS_NULL, "service is null");
    }
}
