package com.hb0730.sys.system.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hb0730.base.R;
import com.hb0730.base.utils.JsonUtil;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.core.data.Option;
import com.hb0730.core.service.BaseService;
import com.hb0730.sys.enums.DictValueTypeEnums;
import com.hb0730.sys.system.model.entity.SysDict;
import com.hb0730.sys.system.model.entity.SysDictItem;
import com.hb0730.sys.system.model.request.dict.SysDictCreateRequest;
import com.hb0730.sys.system.model.request.dict.SysDictQueryRequest;
import com.hb0730.sys.system.model.vo.SysDictVO;
import com.hb0730.sys.system.repository.SysDictRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.hb0730.base.pool.ConstPool.DICT_ITEMS_KEY;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/11
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysDictService extends BaseService<String, SysDictQueryRequest, SysDictVO, SysDict,
        SysDictCreateRequest, SysDictCreateRequest, SysDictRepository> {
    @Autowired
    private SysDictItemService sysDictItemService;


    public R<String> hasCode(String code, String id) {
        boolean exist = repository.isExist(code, id);
        if (exist) {
            return R.NG("编码已存在");
        }
        return R.OK();
    }

    /**
     * 加载字典项
     *
     * @param dictCode 字典编码
     * @return 字典项
     */
    @Cacheable(value = DICT_ITEMS_KEY, key = "#dictCode", unless = "#result == null")
    public List<Option> loadItems(String dictCode) {
        SysDict dict = repository.getByCode(dictCode);
        if (dict == null) {
            return null;
        }
        List<SysDictItem> dictItems = sysDictItemService.listByDictIdOrderBySortAsc(dict.getId());


        List<Option> optionList = new ArrayList<>(dictItems.size());
        for (SysDictItem dictItem : dictItems) {
            String dictType = dict.getDictType();
            DictValueTypeEnums valueTypeEnums = DictValueTypeEnums.of(dictType);
            Option option = new Option();
            option.setLabel(dictItem.getItemText());
            option.setValue(valueTypeEnums.parse(dictItem.getItemValue()));
            option.setDescription(dictItem.getDescription());
            // 额外参数
            String extra = dictItem.getExtra();
            if (StrUtil.isNotBlank(extra)) {
                Map<String, Object> extraMap = JsonUtil.DEFAULT.json2Obj(extra, new TypeReference<Map<String, Object>>() {
                });
                option.setAttrs(extraMap);
            }
            optionList.add(option);
        }

        return optionList;
    }
}
