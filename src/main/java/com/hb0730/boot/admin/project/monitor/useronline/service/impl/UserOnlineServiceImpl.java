package com.hb0730.boot.admin.project.monitor.useronline.service.impl;

import com.google.common.collect.Lists;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.configuration.properties.BootAdminProperties;
import com.hb0730.boot.admin.project.monitor.useronline.model.vo.ParamsVO;
import com.hb0730.boot.admin.project.monitor.useronline.model.vo.UserOnlineVO;
import com.hb0730.boot.admin.project.monitor.useronline.service.IUserOnlineService;
import com.hb0730.boot.admin.security.model.LoginUser;
import com.hb0730.boot.admin.security.service.ITokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Service
public class UserOnlineServiceImpl implements IUserOnlineService {
    private final ITokenService tokenService;
    private final BootAdminProperties properties;

    public UserOnlineServiceImpl(ITokenService tokenService, BootAdminProperties properties) {
        this.tokenService = tokenService;
        this.properties = properties;
    }

    @Override
    public List<UserOnlineVO> getOnlineUser(ParamsVO vo) {
        List<UserOnlineVO> list = getOnline();
        if (Objects.nonNull(vo)) {
            return list.stream().filter((user) -> {
                if (StringUtils.isNotBlank(vo.getIpaddr()) && StringUtils.isNotBlank(vo.getUsername())) {
                    return user.getIpaddr().equals(vo.getIpaddr()) && user.getUsername().equals(vo.getUsername());
                } else if (StringUtils.isNotBlank(vo.getIpaddr())) {
                    return user.getIpaddr().equals(vo.getIpaddr());
                } else if (StringUtils.isNotBlank(vo.getUsername())) {
                    return user.getUsername().equals(vo.getUsername());
                } else {
                    return true;
                }
            }).sorted(Comparator.comparingLong(UserOnlineVO::getLoginTime).reversed()).collect(Collectors.toList());
        }
        return list;
    }

    @Override
    public boolean logout(List<String> token) {
        if (CollectionUtils.isEmpty(token)) {
            return false;
        }
        for (String s : token) {
            tokenService.deleteAccessToken(s);
        }
        return true;
    }

    /**
     * <p>
     * 获取在线缓存用户信息
     * </p>
     *
     * @return 缓存用户
     */
    private List<UserOnlineVO> getOnline() {
        Map<String, UserDetails> online = tokenService.getOnline();
        if (!CollectionUtils.isEmpty(online)) {
            List<UserOnlineVO> lists = Lists.newArrayList();
            for (Map.Entry<String, UserDetails> detailsEntry : online.entrySet()) {
                UserOnlineVO onlineVO = new UserOnlineVO();
                onlineVO.setTokenId(detailsEntry.getKey());
                LoginUser loginUser = (LoginUser) detailsEntry.getValue();
                BeanUtils.updateProperties(loginUser, onlineVO);
                lists.add(onlineVO);
            }
            return lists;
        }
        return Lists.newArrayList();
    }
}
