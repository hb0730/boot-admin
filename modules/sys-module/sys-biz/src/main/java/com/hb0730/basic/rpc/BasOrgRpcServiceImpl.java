package com.hb0730.basic.rpc;

import com.hb0730.base.conf.rpc.server.BaseServerRpcService;
import com.hb0730.basic.domain.BasOrg;
import com.hb0730.basic.rpc.mapstruct.BasOrgMapper;
import com.hb0730.basic.service.IBasOrgService;
import com.hb0730.common.api.JR;
import com.hb0730.rpc.basic.domain.BasOrgDto;
import com.hb0730.rpc.basic.domain.query.BasOrgQuery;
import com.hb0730.rpc.basic.service.BasOrgRpcService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BasOrgRpcServiceImpl extends BaseServerRpcService<BasOrgRpcService> implements BasOrgRpcService {
    private final IBasOrgService basOrgService;
    private final BasOrgMapper basOrgMapper;

    @Override
    public JR<String> checkOrgExpiredForLogin(String orgId) {
        BasOrg userOrg = basOrgService.findByOrgId(orgId);
        if (userOrg == null) {
            return JR.fail("机构不存在");
        }
        String[] orgIds = userOrg.getPath().split(",");
        BasOrg adminOrg = basOrgService.findByOrgId(orgIds[0]);
        if (adminOrg == null) {
            return JR.fail("机构不存在");
        }
        Date usedEndTime = adminOrg.getUsedEndTime();
        if (null == usedEndTime) {
            return JR.ok();
        }
        // 当前时间在使用截止时间之前返回true
        if (usedEndTime.after(new Date())) {
            return JR.ok();
        }
        return JR.fail("系统使用已失效，请联系管理员！");
    }

    @Override
    public JR<List<BasOrgDto>> listDefaultRootQuery(BasOrgQuery query) {
        List<BasOrg> list = basOrgService.listDefaultRootQuery(query);
        List<BasOrgDto> res = basOrgMapper.toDtoList(list);
        return JR.okData(res);
    }

    @Override
    public JR<List<BasOrgDto>> list(BasOrgQuery query) {
        List<BasOrg> list = basOrgService.list(query);
        List<BasOrgDto> res = basOrgMapper.toDtoList(list);
        return JR.okData(res);
    }

    @Override
    public JR<String> save(BasOrgDto dto) {
        JR<String> jr = validate(dto, false);
        if (null != jr) {
            return jr;
        }
        BasOrg parentOrg = basOrgService.findByOrgId(dto.getParentId());

        BasOrg entity = basOrgMapper.toEntity(dto);
        //设置机构路径
        entity.setPath(parentOrg.getPath() + "," + entity.getId());
        // 设置机构层级
        entity.setLevel(parentOrg.getLevel() + 1);
        entity.setSystem(false);
        entity.setSaas(false);

        basOrgService.save(entity);

        return JR.ok();
    }

    @Override
    public JR<String> updateById(BasOrgDto dto) {
        JR<String> jr = validate(dto, true);
        if (null != jr) {
            return jr;
        }
        BasOrg parentOrg = basOrgService.findByOrgId(dto.getParentId());

        BasOrg entity = basOrgMapper.toEntity(dto);
        //设置机构路径
        entity.setPath(parentOrg.getPath() + "," + entity.getId());
        // 设置机构层级
        entity.setLevel(parentOrg.getLevel() + 1);
        entity.setSystem(false);
        entity.setSaas(false);
        basOrgService.updateById(entity);
        return JR.ok();
    }

    @Override
    public JR<String> deleteById(String id) {
        BasOrg orgInfo = basOrgService.findByOrgId(id);
        // 是否顶级机构
        if (Boolean.TRUE.equals(orgInfo.getSystem())) {
            return JR.fail("系统机构不能删除");
        }
        // 是否有子机构
        boolean hasChild = basOrgService.hasChild(id);
        if (hasChild) {
            return JR.fail("请先删除子机构");
        }
        // 是否分配用户
        boolean hasUser = basOrgService.hasUser(id);
        if (hasUser) {
            return JR.fail("请先删除用户");
        }
        basOrgService.deleteById(id);
        return JR.ok();
    }

    @Nullable
    private JR<String> validate(BasOrgDto dto, boolean isUpdate) {
        String parentId = dto.getParentId();
        if (parentId == null) {
            return JR.fail("父级机构不能为空");
        }
        Integer type = dto.getType();
        if (type == null) {
            return JR.fail("机构类型不能为空");
        }
        if (type == 1) {
            return JR.fail("机构类型不正确");
        }
        if (!isUpdate) {
            //验证站点数量是否已经上线
            boolean checkedSiteNum = basOrgService.checkSiteNum(dto.getParentId());
            if (!checkedSiteNum) {
                return JR.fail("站点数量已达上限");
            }
        }
        if (isUpdate) {
            String id = dto.getId();
            if (id == null) {
                return JR.fail("机构id不能为空");
            }
        }

        return null;
    }
}
