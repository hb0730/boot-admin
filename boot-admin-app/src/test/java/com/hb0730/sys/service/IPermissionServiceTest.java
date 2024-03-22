package com.hb0730.sys.service;

import com.hb0730.biz.entity.system.Permission;
import com.hb0730.sys.bean.PermissionQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;


@SpringBootTest
@Slf4j
class IPermissionServiceTest {

    @Test
    @DisplayName("查询根节点")
    void findRootList(@Autowired IPermissionService permissionService) {
        PermissionQuery query = new PermissionQuery();
        List<Permission> permissions = permissionService.listDefaultRootQueryOrderRank(query);
        Assertions.assertTrue(
                permissions.stream().map(Permission::getParentId).allMatch(Objects::isNull),
                "查询的数据中存在父级id不为空的数据"
        );
    }

    @Test
    @DisplayName("查询子节点")
    void findChildList(@Autowired IPermissionService permissionService) {
        PermissionQuery query = new PermissionQuery();
        query.setParentId(1);
        query.setSorts("rank,id");
        List<Permission> permissions = permissionService.listDefaultRootQueryOrderRank(query);
        Assertions.assertTrue(
                permissions.stream().map(Permission::getParentId).allMatch(e -> e.equals(1)),
                "查询的数据中存在父级id不为1的数据"
        );
    }
}