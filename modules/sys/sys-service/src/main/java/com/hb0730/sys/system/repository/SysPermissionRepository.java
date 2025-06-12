package com.hb0730.sys.system.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hb0730.base.utils.CollectionUtil;
import com.hb0730.core.repository.BaseRepository;
import com.hb0730.sys.system.convert.SysPermissionConvert;
import com.hb0730.sys.system.model.entity.SysPermission;
import com.hb0730.sys.system.model.request.permission.SysPermissionCreateRequest;
import com.hb0730.sys.system.model.request.permission.SysPermissionQueryRequest;
import com.hb0730.sys.system.model.vo.SysPermissionVO;
import com.hb0730.sys.system.repository.mapper.SysPermissionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/5/21
 */
@Service
@Slf4j
public class SysPermissionRepository extends BaseRepository<String, SysPermissionQueryRequest, SysPermissionVO,
        SysPermission, SysPermissionCreateRequest, SysPermissionCreateRequest,
        SysPermissionMapper, SysPermissionConvert> {

    /**
     * 列表，根据排序升序
     *
     * @return 列表
     */
    public List<SysPermission> listOrderBySortAsc() {
        return listOrderBySort(true);
    }

    /**
     * 列表，根据排序升序或降序
     *
     * @param asc true 升序 false 降序
     * @return 列表
     */
    public List<SysPermission> listOrderBySort(boolean asc) {
        LambdaQueryWrapper<SysPermission> eq = Wrappers.lambdaQuery(SysPermission.class)
                .orderBy(true, asc, SysPermission::getSort);
        return baseMapper.selectList(eq);
    }

    /**
     * 根据用户id获取权限
     *
     * @param userId 用户id
     * @return 权限
     */
    public List<SysPermission> findByUserId(String userId) {
        return baseMapper.findByUserId(userId);
    }

    /**
     * 根据ID列表获取权限
     *
     * @param ids ID列表
     * @return 权限列表
     */
    public List<SysPermission> listByIdsOrderBySortAsc(List<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return List.of();
        }
        LambdaQueryWrapper<SysPermission> queryWrapper = Wrappers.lambdaQuery(SysPermission.class)
                .in(SysPermission::getId, ids)
                .orderByAsc(SysPermission::getSort);
        return list(queryWrapper);
    }

    /**
     * 统计子节点数量
     *
     * @param parentId 父节点id
     * @return 数量
     */
    public long countByParentId(String parentId) {
        LambdaQueryWrapper<SysPermission> queryWrapper = Wrappers.lambdaQuery(SysPermission.class)
                .eq(SysPermission::getParentId, parentId);
        return count(queryWrapper);
    }

    /**
     * 根据父节点id查询
     *
     * @param parentId 父节点id
     * @return 子节点列表
     */
    public List<SysPermission> findByParentId(String parentId) {
        LambdaQueryWrapper<SysPermission> queryWrapper = Wrappers.lambdaQuery(SysPermission.class)
                .eq(SysPermission::getParentId, parentId);
        return list(queryWrapper);
    }

    /**
     * 修改叶子节点
     *
     * @param id   节点id
     * @param leaf 是否是叶子节点
     * @return 是否成功
     */
    public boolean changeLeafById(String id, Integer leaf) {
        return baseMapper.changeLeafById(id, leaf);
    }


}
