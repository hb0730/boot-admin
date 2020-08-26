package com.hb0730.boot.admin.project.system.user.account.service;

import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.boot.admin.project.system.user.account.model.entity.UserAccountEntity;
import com.hb0730.boot.admin.project.system.user.account.model.vo.UserAccountParams;
import com.hb0730.boot.admin.project.system.user.account.model.vo.UserAccountVO;

/**
 * 用户账号  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IUserAccountService extends IBaseService<Long, UserAccountParams, UserAccountVO, UserAccountEntity> {
    /**
     * 根据用户获取账号信息
     *
     * @param id 用户id
     * @return 账号信息，如果用户id为<code>null</code>,返回<code>null</code>
     */
    UserAccountEntity userAccountByUserId(Long id);

    /**
     * 根据用户账号查找用户账号信息
     *
     * @param username 用户账号
     * @return 账号信息，如果用户账号为<code>null</code>,返回<code>null</code>
     */
    UserAccountEntity userAccountByUsername(String username);
}
