package com.hb0730.boot.admin.commons.utils.ip;

import com.hb0730.boot.admin.commons.utils.json.GsonUtils;
import com.hb0730.boot.admin.commons.utils.spring.SpringUtils;
import com.hb0730.boot.admin.configuration.properties.BootAdminProperties;
import com.hb0730.boot.admin.http.OkHttpClientUtil;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;

/**
 * 获取地址
 *
 * @author bing_huang
 * @date 2020/06/29 14:28
 * @since V2.0
 */
public class AddressUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressUtils.class);
    /**
     * ip 查询地址
     */
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp?ip=%s&json=true";
    /**
     * 未知地址
     */
    public static final String UNKNOWN = "XX XX";

    /**
     * 根据ip获取地址
     *
     * @param ip ip
     * @return 地址
     */
    public static String getRealAddressByIp(String ip) {
        String str = UNKNOWN;
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            LOGGER.warn("根据ip:{}获取地址位置异常", ip);
            return str;
        }
        if (SpringUtils.getBean(BootAdminProperties.class).isAddressEnabled()) {
            try {

                String newIpUrl = String.format(IP_URL, ip);
                Response response = OkHttpClientUtil.getInstance().getData(newIpUrl);
                ResponseBody body = response.body();
                if (Objects.isNull(body)) {
                    LOGGER.warn("根据ip:{}获取地址位置异常", ip);
                    return str;
                }
                String data = body.string();
                Map<String, String> map = GsonUtils.json2Maps(data);
                str = String.format("%s %s", map.get("city"), map.get("region"));
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("根据IP:{},获取地理位置失败,e:{}", ip, e.getMessage());
            }

        }
        return str;
    }


}
