package com.hb0730.boot.admin.utils.lang;

import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 时间
 *
 * @author bing_huang
 * @date 2020/07/18 13:48
 * @since V1.0
 */
public class DateUtils {
    private DateUtils() {
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    @Nonnull
    public static Date now() {
        return new Date();
    }

    /**
     * 时间追加
     *
     * @param date     时间 不为null
     * @param time     时间 不能小于1
     * @param timeUnit 时间类型 不为空
     * @return 时间完后时间
     */
    public static Date add(@Nonnull Date date, long time, @Nonnull TimeUnit timeUnit) {
        Assert.notNull(date, "Date must not be null");
        Assert.isTrue(time >= 0, "Addition time must not be less than 1");
        Assert.notNull(timeUnit, "Time unit must not be null");
        Date result;

        int timeIntValue;

        if (time > Integer.MAX_VALUE) {
            timeIntValue = Integer.MAX_VALUE;
        } else {
            timeIntValue = Long.valueOf(time).intValue();
        }

        // Calc the expiry time
        switch (timeUnit) {
            case DAYS:
                result = org.apache.commons.lang3.time.DateUtils.addDays(date, timeIntValue);
                break;
            case HOURS:
                result = org.apache.commons.lang3.time.DateUtils.addHours(date, timeIntValue);
                break;
            case MINUTES:
                result = org.apache.commons.lang3.time.DateUtils.addMinutes(date, timeIntValue);
                break;
            case SECONDS:
                result = org.apache.commons.lang3.time.DateUtils.addSeconds(date, timeIntValue);
                break;
            case MILLISECONDS:
                result = org.apache.commons.lang3.time.DateUtils.addMilliseconds(date, timeIntValue);
                break;
            default:
                result = date;
        }
        return result;
    }
}
