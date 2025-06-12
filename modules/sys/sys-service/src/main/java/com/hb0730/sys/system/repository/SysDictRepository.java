package com.hb0730.sys.system.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.core.repository.BaseRepository;
import com.hb0730.sys.system.convert.SysDictConvert;
import com.hb0730.sys.system.model.entity.SysDict;
import com.hb0730.sys.system.model.request.dict.SysDictCreateRequest;
import com.hb0730.sys.system.model.request.dict.SysDictQueryRequest;
import com.hb0730.sys.system.model.vo.SysDictVO;
import com.hb0730.sys.system.repository.mapper.SysDictMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/5/21
 */
@Service
@Slf4j
public class SysDictRepository extends BaseRepository<String, SysDictQueryRequest, SysDictVO, SysDict,
        SysDictCreateRequest, SysDictCreateRequest, SysDictMapper, SysDictConvert> {


    /**
     * 是否存在
     *
     * @param dictCode 字典编码
     * @param id       需要排除的id
     * @return true 存在
     */
    public boolean isExist(String dictCode, String id) {
        LambdaQueryWrapper<SysDict> eq = Wrappers.lambdaQuery(SysDict.class)
                .eq(SysDict::getDictCode, dictCode);
        if (StrUtil.isNotBlank(id)) {
            eq.ne(SysDict::getId, id);
        }
        return baseMapper.of(eq).present();
    }

    /**
     * 根据编码查询
     *
     * @param code 编码
     * @return 字典
     */
    public SysDict getByCode(String code) {
        return baseMapper.of(
                Wrappers.lambdaQuery(SysDict.class)
                        .eq(SysDict::getDictCode, code)
        ).one();
    }
}
