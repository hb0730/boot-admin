package com.hb0730.rpc.sys.system.domain.query;

import com.blinkfox.fenix.specification.annotation.GreaterThanEqual;
import com.blinkfox.fenix.specification.annotation.LessThanEqual;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hb0730.commons.BaseQuery;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/2
 */
@Getter
@Setter
public class NoticeQuery extends BaseQuery {
    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 公告开始时间
     */
    @GreaterThanEqual
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date noticeTimeStart;

    /**
     * 公告结束时间
     */
    @LessThanEqual
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date noticeTimeEnd;
}
