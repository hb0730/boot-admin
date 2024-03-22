package com.hb0730.sys.rpc;

import com.hb0730.biz.dto.sys.system.DeptDto;
import com.hb0730.biz.entity.system.Dept;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.conf.rpc.rpc.BaseRpcService;
import com.hb0730.sys.bean.DeptQuery;
import com.hb0730.sys.rpc.mapstruct.DeptMapper;
import com.hb0730.sys.service.DeptRpcService;
import com.hb0730.sys.service.IDeptService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/18
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DeptRpcServiceImpl extends BaseRpcService<DeptRpcService> implements DeptRpcService {
    private final DeptMapper deptMapper;

    private final IDeptService deptService;

    @Override
    public JR<Boolean> existsByCodeAndIdNot(String code, @Nullable Integer id) {
        Boolean exists = deptService.existsByCodeAndIdNot(code, id);
        return JR.okData(exists);
    }

    @Override
    public JR<List<DeptDto>> list(DeptQuery query) {
        List<Dept> list = deptService.list(query);
        List<DeptDto> res = deptMapper.toDtoList(list);
        return JR.okData(res);
    }

    @Override
    public JR<JsfPage<DeptDto>> page(DeptQuery query) {
        Page<Dept> page = deptService.page(query);
        List<DeptDto> data = deptMapper.toDtoList(page.getContent());
        JsfPage<DeptDto> re = JsfPage.of(page, data);
        return JR.okData(re);
    }

    @Override
    public JR<String> save(DeptDto dept) {
        Dept entity = deptMapper.toEntity(dept);
        deptService.save(entity);
        return JR.ok();
    }

    @Override
    public JR<String> updateById(DeptDto dept) {
        Dept entity = deptMapper.toEntity(dept);
        deptService.update(entity);
        return JR.ok();
    }

    @Override
    public JR<String> deleteById(Integer id) {
        deptService.delete(id);
        return JR.ok();
    }
}
