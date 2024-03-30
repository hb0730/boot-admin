package com.hb0730.basic.rpc;

import com.hb0730.base.BaseTest;
import com.hb0730.rpc.sys.tenant.domain.TenantSmallDto;
import com.hb0730.rpc.sys.tenant.service.TenantOrgRpcService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TenantOrgRpcServiceImplTest extends BaseTest {

    @Test
    @DisplayName("新增商户")
    void saveTenantOrganization(@Autowired TenantOrgRpcService service) {
        TenantSmallDto small = new TenantSmallDto();
        small.setSysCode("T001");
        small.setName("测试厂商");
        small.setLinkMan("测试联系人");
        small.setLinkTel("12345678901");
        small.setLinkEmail("test@qq.com");
        small.setProductId(1L);
        small.setEnabled(true);
        service.saveTenantOrganization(small);
    }

    @Test
    @DisplayName("更新商户的产品")
    void updateTenantOrganization(@Autowired TenantOrgRpcService service) {
        TenantSmallDto small = new TenantSmallDto();
        small.setId("1773198450129768448");
        small.setSysCode("T001");
        small.setName("测试厂商");
        small.setLinkMan("测试联系人");
        small.setLinkTel("12345678901");
        small.setLinkEmail("test@qq.com");
        small.setEnabled(true);
        small.setProductId(2L);
        service.updateTenantOrganization(small);

    }
}