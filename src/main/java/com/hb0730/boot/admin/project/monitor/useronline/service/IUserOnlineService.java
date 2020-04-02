package com.hb0730.boot.admin.project.monitor.useronline.service;

import com.hb0730.boot.admin.project.monitor.useronline.model.vo.ParamsVO;
import com.hb0730.boot.admin.project.monitor.useronline.model.vo.UserOnlineVO;

import java.util.List;

/**
 * <p>
 * 在线用户
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface IUserOnlineService {


    /**
     * <p>
     * 获取在线用户
     * </p>
     *
     * @return 在线用户
     */
    List<UserOnlineVO> getOnlineUser(ParamsVO vo);

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
