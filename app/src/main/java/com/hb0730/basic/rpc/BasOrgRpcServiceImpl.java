package com.hb0730.basic.rpc;

import com.hb0730.base.conf.server.BaseServerRpcService;
import com.hb0730.basic.domain.BasOrg;
import com.hb0730.basic.service.IBasOrgService;
import com.hb0730.commons.JR;
import com.hb0730.rpc.basic.service.BasOrgRpcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BasOrgRpcServiceImpl extends BaseServerRpcService<BasOrgRpcService> implements BasOrgRpcService {
    private final IBasOrgService basOrgService;

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
}
