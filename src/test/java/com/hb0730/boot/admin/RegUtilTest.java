package com.hb0730.boot.admin;

import com.hb0730.commons.lang.RegexUtils;
import com.hb0730.commons.lang.constants.RegexConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RegExUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/3/4
 * @since 1.0.0
 */
@Slf4j
public class RegUtilTest {
    @Test
    public void httpRegTest() {
        boolean match = RegExUtils.isMatch("^(http[s]{0,1})://([\\w.]+\\/?)\\S*", "https://github" +
            ".com/xiaoxian521/vue-pure-admin");
        Assert.assertTrue("非法路径", match);
    }
}
