package com.hb0730.basic.rpc.cache;

import com.hb0730.base.enums.basic.OrgEnum;
import com.hb0730.base.utils.JsonUtil;
import com.hb0730.basic.domain.BasOrg;
import com.hb0730.cache.core.BootAdminCache;
import com.hb0730.cache.core.CacheUtil;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 厂商信息缓存类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/2
 */
@Component
@Slf4j
public class BasOrgCache implements CacheUtil {
    @Resource
    @Lazy
    private BootAdminCache cache;

    @Getter
    public enum KeyValue implements CacheUtil.KeyValue {
        /**
         * 厂商基础信息
         * <p> 过期时间：30天
         */
        ORG_INFO("ORG_INFO", EXPIRE_TIME, "厂商基础信息"),
        /**
         * 厂商基础信息
         * <p> 过期时间：30天
         */
        ORG_INFO_BY_SYS_CODE("ORG_INFO_BY_SYS_CODE", EXPIRE_TIME, "厂商基础信息"),
        ;
        private final String prefix;
        /**
         * 过期时间（秒）
         */
        private final int expire;
        /**
         * 描述
         */
        private final String desc;

        KeyValue(String prefix, int expire, String desc) {
            this.prefix = prefix;
            this.expire = expire;
            this.desc = desc;
        }
    }

    /**
     * 缓存厂商基础信息
     *
     * @param basOrg 厂商基础信息
     */
    public void putOrgInfo(BasOrg basOrg) {
        String cacheKey = getCacheKey(
                KeyValue.ORG_INFO,
                basOrg.getId()
        );
        cache.setString(
                cacheKey,
                JsonUtil.DEFAULT.toJson(basOrg),
                KeyValue.ORG_INFO.expire
        );

        if (OrgEnum.OrgTypeEnums.BAS_ORG_TYPE_01.getValue().equals(
                basOrg.getType()
        )) {
            String cacheKey2 = getCacheKey(
                    KeyValue.ORG_INFO,
                    basOrg.getId()
            );
            cache.setString(
                    cacheKey2,
                    JsonUtil.DEFAULT.toJson(basOrg),
                    KeyValue.ORG_INFO.expire
            );

            String cacheKey3 = getCacheKey(
                    KeyValue.ORG_INFO_BY_SYS_CODE,
                    basOrg.getSysCode()
            );
            cache.setString(
                    cacheKey3,
                    JsonUtil.DEFAULT.toJson(basOrg),
                    KeyValue.ORG_INFO.expire
            );
        }
    }

    /**
     * 获取厂商信息
     *
     * @param id 厂商id
     * @return 厂商信息
     */
    public Optional<BasOrg> getOrderInfo(String id) {
        String cacheKey = getCacheKey(
                KeyValue.ORG_INFO,
                id
        );
        String json = cache.getString(cacheKey);
        return Optional.ofNullable(JsonUtil.DEFAULT.json2Obj(json, BasOrg.class));
    }

    /**
     * 获取厂商信息
     *
     * @param sysCode 商户识别码
     * @return 厂商信息
     */
    public Optional<BasOrg> getOrderInfoBySysCode(String sysCode) {
        String cacheKey = getCacheKey(
                KeyValue.ORG_INFO_BY_SYS_CODE,
                sysCode
        );
        String json = cache.getString(cacheKey);
        return Optional.ofNullable(JsonUtil.DEFAULT.json2Obj(json, BasOrg.class));
    }
}
