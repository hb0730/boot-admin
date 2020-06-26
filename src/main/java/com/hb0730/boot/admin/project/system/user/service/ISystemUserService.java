package com.hb0730.boot.admin.project.system.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.hb0730.boot.admin.commons.domain.service.IBaseService;
import com.hb0730.boot.admin.commons.domain.service.IExportService;
import com.hb0730.boot.admin.project.system.user.model.dto.LoginUserDTO;
import com.hb0730.boot.admin.project.system.user.model.dto.UserExcelDTO;
import com.hb0730.boot.admin.project.system.user.model.entity.SystemUserEntity;
import com.hb0730.boot.admin.project.system.user.model.vo.SystemUserVO;
import com.hb0730.boot.admin.project.system.user.model.vo.UserParams;
import com.hb0730.boot.admin.project.system.user.model.vo.UserVO;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 系统用户  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-24
 */
public interface ISystemUserService extends IBaseService<UserParams, SystemUserEntity>, IService<SystemUserEntity>, IExportService<UserExcelDTO> {

    /**
     * 分页查询
     *
     * @param params 过滤参数
     * @return 分页后得数据
     */
    Page<SystemUserVO> page(@NonNull UserParams params);

    /**
     * 用户保存
     *
     * @param vo 用户信息
     * @return 是否成功
     */
    boolean save(@NonNull UserVO vo);

    /**
     * 获取用户详情
     *
     * @param userId 用户id
     * @return 用户详情
     */
    UserVO getUserInfo(@NotNull Long userId);

    /**
     * 修改用户
     *
     * @param vo     用户信息
     * @param userId 用户id
     * @return 是否成功
     */
    boolean updateUser(@NonNull UserVO vo, @NonNull Long userId);


    /**
     * 根据用户账号查询用户信息
     *
     * @param username 用户账号
     * @return 用户登录信息
     */
    LoginUserDTO loadUserByUsername(@NonNull String username);


    /**
     * <p>
     * 重置面
     * </p>
     *
     * @param id 用户id
     * @return 是否成功
     */
    boolean resetPassword(@NonNull Long id);

    /**
     * <p>
     * 导出
     * </p>
     *
     * @param params 过滤参数
     * @return 数据
     */
    List<UserExcelDTO> export(UserParams params);
}
