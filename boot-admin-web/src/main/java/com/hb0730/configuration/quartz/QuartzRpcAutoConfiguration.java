package com.hb0730.configuration.quartz;

import cn.hutool.crypto.digest.DigestUtil;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.conf.rpc.RpcRemoteProperties;
import com.hb0730.sys.quartz.rpcservice.IQuartzRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static com.hb0730.base.constant.RpcConstant.HEADER_REQSEQ_KEY;
import static com.hb0730.base.constant.RpcConstant.HEADER_TIMESTAMP_KEY;
import static com.hb0730.base.constant.RpcConstant.HEADER_TOKEN_KEY;
import static com.hb0730.base.constant.RpcConstant.HEADER_USER_KEY;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/4
 */
@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "boot.admin.rpc.remote.job", name = "enabled", havingValue = "true")
public class QuartzRpcAutoConfiguration {

    /**
     * Job 查询远程服务
     *
     * @param rpcJobRemoteProperties rpcJobRemoteProperties 配置
     * @return IQuartzRemoteService job远程服务
     */
    @Bean
    @ConditionalOnMissingBean
    public IQuartzRemoteService queryRemoteService(RpcRemoteProperties rpcJobRemoteProperties) {
        RpcRemoteProperties.Job job = rpcJobRemoteProperties.getJob();

        String timestamp = System.currentTimeMillis() + "";
        String sign = sign(job.getSecret(), timestamp);
        RestClient quartzRestClient = RestClient.builder()
                .baseUrl(job.getServer())
                .defaultHeaders(
                        (request) -> {
                            request.add(HEADER_REQSEQ_KEY, StrUtil.getStr(MDC.get(HEADER_REQSEQ_KEY), "NONONONONONONO"));
                            request.add(HEADER_USER_KEY, StrUtil.getStr(MDC.get(HEADER_USER_KEY), "SYSTEM_AUTO"));
                            request.add(HEADER_TOKEN_KEY, sign);
                            request.add(HEADER_TIMESTAMP_KEY, timestamp);
                        }
                )
                .requestInterceptor((request, body, execution) -> {
                    log.info("请求: url {}", request.getURI().toString());
                    return execution.execute(request, body);
                })
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(quartzRestClient);
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builder()
                .exchangeAdapter(adapter)
                .build();
        return proxyFactory.createClient(IQuartzRemoteService.class);
    }

    /**
     * 签名
     *
     * @param secret    密钥
     * @param timestamp 时间戳
     * @return 签名
     */
    private String sign(String secret, String timestamp) {
        return DigestUtil.md5Hex(secret + timestamp + timestamp + secret);
    }
}
