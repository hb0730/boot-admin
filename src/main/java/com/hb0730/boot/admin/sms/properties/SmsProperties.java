package com.hb0730.boot.admin.sms.properties;

import com.hb0730.boot.admin.commons.constant.enums.SmsTypeEnum;
import lombok.Data;
import lombok.ToString;
import org.springframework.lang.Nullable;

/**
 * @author bing_huang
 * @date 2020/07/03 10:41
 * @since V1.0
 */
@Data
@ToString
public class SmsProperties {

    /**
     * 检查是否支持给定类型
     *
     * @param type 附件类型
     * @return true为支持类型
     */
  public   boolean supportType(@Nullable SmsTypeEnum type) {
        return false;
    }

}
