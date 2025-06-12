package com.hb0730.sys.system.service;

import com.hb0730.base.PairEnum;
import com.hb0730.base.exception.BasicException;
import com.hb0730.base.utils.CollectionUtil;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.core.service.BaseService;
import com.hb0730.sys.enums.MenuTypeEnums;
import com.hb0730.sys.system.model.entity.SysPermission;
import com.hb0730.sys.system.model.request.permission.SysPermissionCreateRequest;
import com.hb0730.sys.system.model.request.permission.SysPermissionQueryRequest;
import com.hb0730.sys.system.model.request.permission.SysPermissionTreeQueryRequest;
import com.hb0730.sys.system.model.vo.SysPermissionTreeVO;
import com.hb0730.sys.system.model.vo.SysPermissionVO;
import com.hb0730.sys.system.repository.SysPermissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单与权限
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
@Service
@Slf4j
public class SysPermissionService extends BaseService<String, SysPermissionQueryRequest, SysPermissionVO,
        SysPermission, SysPermissionCreateRequest, SysPermissionCreateRequest, SysPermissionRepository> {

    /**
     * 菜单树
     *
     * @return 菜单树
     */
    public List<SysPermissionTreeVO> tree(SysPermissionTreeQueryRequest query) {
        List<SysPermission> tree = repository.listOrderBySortAsc();

        return repository.getMapstruct().toTreeVo(tree);
    }

    /**
     * 根据用户id获取权限
     *
     * @param userId 用户id
     * @return 权限
     */
    public List<SysPermission> findByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    /**
     * 根据ID列表获取权限
     *
     * @param ids ID列表
     * @return 权限列表
     */
    public List<SysPermission> listByIdsOrderBySortAsc(List<String> ids) {
        return repository.listByIdsOrderBySortAsc(ids);
    }

    /**
     * 创建
     *
     * @param req req
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean create(SysPermissionCreateRequest req) {
        check(req);
        String parentId = req.getParentId();
        if (StrUtil.isBlank(parentId)) {
            req.setParentId(null);
        }
        req.setIsLeaf(true);
        SysPermission entity = repository.getMapstruct().createReqToEntity(req);
        save(entity);
        if (StrUtil.isNotBlank(parentId)) {
            this.repository.changeLeafById(parentId, 0);
        }
        return true;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(String id, SysPermissionCreateRequest req) {
        check(req);
        SysPermission entity = repository.getMapstruct().updateReqToEntity(req);
        entity.setId(id);
        // steps 1: 更新parentId
        if (StrUtil.isBlank(req.getParentId())) {
            entity.setParentId(null);
        }
        // steps 2: 更新是否是叶子节点
        long count = repository.countByParentId(id);
        entity.setIsLeaf(count == 0);

        // 如果当前菜单的父菜单变了，则需要修改新父菜单和老父菜单的，叶子节点状态
        SysPermission permission = getById(id);
        String parentId = permission.getParentId();
        String pId = entity.getParentId();
        if ((StrUtil.isNotBlank(pId) && !pId.equals(parentId)) || StrUtil.isBlank(pId) && StrUtil.isNotBlank(parentId)) {
            // 1.设置新的父菜单不为叶子节点
            repository.changeLeafById(pId, 0);
            // 2.判断老的父菜单是否还有子菜单
            long count1 = repository.countByParentId(parentId);
            if (count1 == 1) {
                if (StrUtil.isNotBlank(parentId)) {
                    repository.changeLeafById(parentId, 1);
                }
            }
        }
        return updateById(entity);
    }

    /**
     * 删除
     *
     * @param id id
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deletePermission(String id) {
        SysPermission permission = getById(id);
        if (permission == null) {
            return false;
        }
        String parentId = permission.getParentId();
        if (StrUtil.isNotBlank(parentId)) {
            long count = repository.countByParentId(parentId);
            if (count == 1) {
                repository.changeLeafById(parentId, 1);
            }
        }
        boolean res = repository.deleteById(id);
        // 删除子节点
        removeChildrenBy(id);
        return res;
    }


    /**
     * 删除子节点
     *
     * @param parentId 父级id
     */
    private void removeChildrenBy(String parentId) {
        List<SysPermission> list = repository.findByParentId(parentId);
        if (CollectionUtil.isNotEmpty(list)) {
            list.forEach(item -> {
                removeChildrenBy(item.getId());
                repository.deleteById(item.getId());
            });
        }
    }

    /**
     * 链式检测
     *
     * @param dto dto
     */
    public void check(SysPermissionCreateRequest dto) {
        Integer menuType = dto.getMenuType();
        MenuTypeEnums menuTypeEnums = PairEnum.of(MenuTypeEnums.class, menuType)
                .orElseThrow(() -> new BasicException("菜单类型错误"));
        switch (menuTypeEnums) {
            case MENU:
                checkMenu(dto);
                break;
            case BUTTON:
                checkButton(dto);
                break;
            case EXTERNAL:
                checkLink(dto);
                break;
            case FRAME:
                checkIframe(dto);
                break;
            default:
                throw new BasicException("菜单类型错误");
        }
    }

    /**
     * 类型为：菜单，参数检测
     */
    public void checkMenu(SysPermissionCreateRequest dto) {
        String title = dto.getTitle();
        if (title == null || title.isEmpty()) {
            throw new BasicException("菜单名称不能为空");
        }
        String routeName = dto.getRouteName();
        if (routeName == null || routeName.isEmpty()) {
            throw new BasicException("路由名称不能为空");
        }
        String routePath = dto.getRoutePath();
        if (routePath == null || routePath.isEmpty()) {
            throw new BasicException("路由路径不能为空");
        }

    }

    /**
     * 类型为：按钮，参数检测
     */
    public void checkButton(SysPermissionCreateRequest dto) {
        String title = dto.getTitle();
        if (title == null || title.isEmpty()) {
            throw new BasicException("按钮名称不能为空");
        }
        String perms = dto.getPerms();
        if (perms == null || perms.isEmpty()) {
            throw new BasicException("按钮权限不能为空");
        }

    }

    /**
     * 类型为：外链，参数检测
     */
    public void checkLink(SysPermissionCreateRequest dto) {
        String title = dto.getTitle();
        if (title == null || title.isEmpty()) {
            throw new BasicException("外链名称不能为空");
        }
    }

    /**
     * 类型为：iframe，参数检测
     */
    public void checkIframe(SysPermissionCreateRequest dto) {
        String title = dto.getTitle();
        if (title == null || title.isEmpty()) {
            throw new BasicException("iframe名称不能为空");
        }
        String frameSrc = dto.getFrameSrc();
        if (frameSrc == null || frameSrc.isEmpty()) {
            throw new BasicException("iframe路径不能为空");
        }

    }
}
