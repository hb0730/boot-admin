package com.hb0730.boot.admin.project.monitor.online.service;

import com.hb0730.boot.admin.project.monitor.online.model.dto.UserOnlineDTO;
import com.hb0730.boot.admin.project.monitor.online.model.query.UserOnlineParams;
import com.hb0730.commons.json.exceptions.JsonException;

import java.util.List;

/**
 * 在线用户
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IUserOnlineService {
    /**
     * 获取在线用户
     *
     * @return 在线用户
     */
    List<UserOnlineDTO> getOnlineUser(UserOnlineParams vo);

    /**
     * <p>
     * 强退
     * </p>
     *
     * @param token token
     * @return 是否成功
     */
    boolean logout(List<String> token);
}
