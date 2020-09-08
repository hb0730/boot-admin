package com.hb0730.boot.admin.project.monitor.online.service.impl;

import com.google.common.collect.Lists;
import com.hb0730.boot.admin.project.monitor.online.model.dto.UserOnlineDTO;
import com.hb0730.boot.admin.project.monitor.online.model.query.UserOnlineParams;
import com.hb0730.boot.admin.project.monitor.online.service.IUserOnlineService;
import com.hb0730.boot.admin.security.model.User;
import com.hb0730.boot.admin.token.ITokenService;
import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.collection.CollectionUtils;
import com.hb0730.commons.spring.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 在线用户
 *
 * @author bing_huang
 * @since 3.0.0
 */
@RequiredArgsConstructor
@Service
public class UerOnlineServiceImpl implements IUserOnlineService {
    private final ITokenService tokenService;

    @Override
    public List<UserOnlineDTO> getOnlineUser(UserOnlineParams vo) {
        List<UserOnlineDTO> list = getOnline();
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
            }).sorted(Comparator.comparing(UserOnlineDTO::getLoginTime).reversed()).collect(Collectors.toList());
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
     * 获取在线缓存用户信息
     *
     * @return 缓存用户
     */
    private List<UserOnlineDTO> getOnline() {
        Map<String, UserDetails> online = tokenService.getOnline();
        if (!CollectionUtils.isEmpty(online)) {
            List<UserOnlineDTO> lists = Lists.newArrayList();
            for (Map.Entry<String, UserDetails> detailsEntry : online.entrySet()) {
                UserOnlineDTO dto = new UserOnlineDTO();
                dto.setTokenId(detailsEntry.getKey());
                User loginUser = (User) detailsEntry.getValue();
                BeanUtils.updateProperties(loginUser, dto);
                lists.add(dto);
            }
            return lists;
        }
        return Lists.newArrayList();
    }
}
