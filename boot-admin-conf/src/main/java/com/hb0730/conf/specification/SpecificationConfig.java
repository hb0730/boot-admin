package com.hb0730.conf.specification;

import com.blinkfox.fenix.config.FenixConfig;
import com.blinkfox.fenix.config.FenixConfigManager;
import com.hb0730.base.specification.handler.QueryPredicateHandler;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/19
 */
@Configuration
public class SpecificationConfig {
    public SpecificationConfig() {
        FenixConfigManager.getInstance().initLoad();
        FenixConfig.add(new QueryPredicateHandler());
    }
}
