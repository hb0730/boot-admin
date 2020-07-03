package com.hb0730.boot.admin.sms.params;

import lombok.Data;
import lombok.ToString;

/**
 * @author bing_huang
 * @date 2020/07/03 10:46
 * @since V1.0
 */
@Data
@ToString
public class BaseSmsParams {


    /**
     * 模板code
     */
    private String templateId;
}
