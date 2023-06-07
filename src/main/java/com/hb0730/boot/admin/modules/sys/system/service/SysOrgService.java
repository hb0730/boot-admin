package com.hb0730.boot.admin.modules.sys.system.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.base.R;
import com.hb0730.boot.admin.core.service.BaseServiceImpl;
import com.hb0730.boot.admin.data.domain.BasePage;
import com.hb0730.boot.admin.modules.sys.system.mapper.SysOrgMapper;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysOrganization;
import com.hb0730.boot.admin.modules.sys.system.model.query.OrganizationQuery;
import com.hb0730.boot.admin.modules.sys.system.model.vo.OrganizationTree;
import com.hb0730.boot.admin.modules.sys.system.model.vo.OrganizationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 机构 Service
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/3
 */
@Service
@Slf4j
public class SysOrgService extends BaseServiceImpl<SysOrgMapper, SysOrganization> {

    /**
     * 获取机构分页
     *
     * @param query .
     * @return .
     */
    public BasePage<OrganizationVO> queryPage(OrganizationQuery query) {
        Page<OrganizationVO> page = new Page<>(query.getCurrent(), query.getSize());
        List<OrganizationVO> records = this.baseMapper.queryPage(page, query);
        return new BasePage<>(page.getCurrent(), page.getSize(), page.getTotal(), records);
    }

    /**
     * 获取机构列表
     *
     * @param query .
     * @return .
     */
    public List<OrganizationVO> queryList(OrganizationQuery query) {
        return this.baseMapper.queryPage(null, query);
    }

    /**
     * 构建机构树
     *
     * @param query .
     * @return .
     */
    public List<OrganizationTree> queryTree(OrganizationQuery query) {
        List<OrganizationVO> listData = this.queryList(query);
        return buildTree(listData);
    }

    /**
     * 保存机构
     *
     * @param vo .
     * @return .
     */
    public R<OrganizationVO> save(OrganizationVO vo) {
        String parentId = vo.getParentId();
        SysOrganization parentOrg = this.getById(parentId);
        if (null == parentOrg) {
            return R.NG("父机构不存在");
        }
        // set Id
        vo.setId(IdUtil.fastSimpleUUID());
        // set Id
        vo.setCode(IdUtil.getSnowflakeNextIdStr());
        // 机构path = 上级网点path+本级ID
        vo.setPath(parentOrg.getPath() + "," + vo.getId());
        // save
        SysOrganization entity = BeanUtil.toBean(vo, SysOrganization.class);
        save(entity);
        return R.OK(vo);
    }

    /**
     * 更新机构
     *
     * @param id .
     * @param vo .
     * @return .
     */
    public R<OrganizationVO> updateById(String id, OrganizationVO vo) {
        SysOrganization organization = getById(id);
        BeanUtil.copyProperties(vo, organization, "id", "code", "path", "isSystem");
        updateById(organization);
        return R.OK(vo);
    }

    /**
     * 批量删除
     *
     * @param ids .
     * @return .
     */
    @Transactional(rollbackFor = Exception.class)
    public R<String> batchDel(List<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return R.NG("参数为空");
        }
        List<SysOrganization> organizations = this.listByIds(ids);
        if (CollectionUtil.isEmpty(organizations)) {
            return R.NG("机构不存在");
        }
        if (organizations.stream().anyMatch(e -> e.getIsSystem() == 1)) {
            return R.NG("系统机构不允许删除");
        }
        this.removeByIds(ids);
        return R.OK();
    }

    public List<OrganizationTree> buildTree(List<OrganizationVO> listData) {
        List<OrganizationTree> treeList = BeanUtil.copyToList(listData, OrganizationTree.class);
        List<OrganizationTree> treeDataList = new ArrayList<>();
        Set<String> ids = new HashSet<>();
        for (OrganizationTree treeData : treeList) {
            if (StrUtil.isBlank(treeData.getParentId())) {
                treeDataList.add(treeData);
            }
            for (OrganizationTree tree : treeList) {
                if (treeData.getId().equals(tree.getParentId())) {
                    if (treeData.getChildren() == null) {
                        treeData.setChildren(new ArrayList<>());
                    }
                    treeData.getChildren().add(tree);

                    ids.add(tree.getId());
                }
            }
        }
        if (treeDataList.size() == 0) {
            treeDataList = treeList.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        return treeDataList;
    }
}
