package com.hb0730.boot.admin.project.system.option.service.impl;

import com.hb0730.boot.admin.commons.enums.PropertyEnum;
import com.hb0730.boot.admin.commons.utils.OptionCacheUtils;
import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.event.option.OptionUpdatedEvent;
import com.hb0730.boot.admin.project.system.option.mapper.IOptionMapper;
import com.hb0730.boot.admin.project.system.option.model.dto.OptionDTO;
import com.hb0730.boot.admin.project.system.option.model.entity.OptionEntity;
import com.hb0730.boot.admin.project.system.option.model.query.OptionParams;
import com.hb0730.boot.admin.project.system.option.service.IOptionService;
import com.hb0730.commons.cache.Cache;
import com.hb0730.commons.spring.BeanUtils;
import com.sun.corba.se.impl.util.RepositoryId;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.hb0730.boot.admin.commons.constant.RedisConstant.OPTIONS_KEY_PREFIX;
import static com.hb0730.boot.admin.commons.constant.RedisConstant.OPTION_VALUE;

/**
 * options选项  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class OptionServiceImpl extends SuperBaseServiceImpl<Long, OptionParams, OptionDTO, OptionEntity, IOptionMapper> implements IOptionService {
    private final ApplicationEventPublisher eventPublisher;
    @Resource
    private Cache<String, Map<String, Object>> redisCache;
    private final Map<String, PropertyEnum> propertyEnumMap;

    public OptionServiceImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
        this.propertyEnumMap = Collections.unmodifiableMap(PropertyEnum.getValuePropertyEnumMap());
    }

    @Override
    public void save(@Nonnull String key, String value) {
        Assert.hasText(key, "key不为空");
        save(Collections.singletonMap(key, value));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Map<String, Object> options) {
        if (CollectionUtils.isEmpty(options)) {
            return;
        }
        List<OptionEntity> insert = new LinkedList<>();
        List<OptionEntity> update = new LinkedList<>();

        List<OptionEntity> list = super.list();
        Map<String, OptionEntity> optionsMap = BeanUtils.convertToMap(list, OptionEntity::getOptionKey);
        options.forEach((key, value) -> {
            OptionEntity entity = optionsMap.get(key);
            if (entity == null || !StringUtils.equals(entity.getOptionValue(), value.toString())) {
                OptionEntity option = new OptionEntity();
                option.setOptionKey(key);
                option.setOptionValue(value.toString());
                if (entity == null) {
                    insert.add(option);
                } else if (!StringUtils.equals(entity.getOptionValue(), value.toString())) {
                    entity.setOptionKey(key);
                    entity.setOptionValue(value.toString());
                    update.add(entity);
                }
            }
        });

        super.saveBatch(insert);
        super.updateBatchById(update);
        // 刷新事件
        if (!CollectionUtils.isEmpty(insert) || !CollectionUtils.isEmpty(update)) {
            // If there is something changed
            publishOptionUpdatedEvent();
        }
    }

    @Override
    public boolean save(@Nonnull OptionDTO dto) {
        Assert.notNull(dto, "保存信息不为空");
        boolean result = super.save(dto);
        publishOptionUpdatedEvent();
        return result;
    }

    @Override
    @Nonnull
    public Map<String, Object> listOptions() {
        return OptionCacheUtils.getCacheValue(OPTION_VALUE).orElseGet(() -> {
            // 可做成缓存
            List<OptionEntity> list = super.list();
            Set<String> keys = list.stream().map(OptionEntity::getOptionKey).collect(Collectors.toSet());
            Map<String, Object> userDefinedOptionsMap = BeanUtils.convertToMap(list, OptionEntity::getOptionKey, data -> {
                String key = data.getOptionKey();
                PropertyEnum propertyEnum = propertyEnumMap.get(key);
                if (propertyEnum == null) {
                    return data.getOptionValue();
                }

                return PropertyEnum.convertTo(data.getOptionValue(), propertyEnum);
            });

            Map<String, Object> result = new HashMap<>(userDefinedOptionsMap);

            propertyEnumMap.keySet()
                    .stream()
                    .filter(key -> !keys.contains(key))
                    .forEach(key -> {
                        PropertyEnum propertyEnum = propertyEnumMap.get(key);
                        if (StringUtils.isBlank(propertyEnum.defaultValue())) {
                            return;
                        }

                        result.put(key, PropertyEnum.convertTo(propertyEnum.defaultValue(), propertyEnum));
                    });
            setCache(result);
            return result;
        });
    }

    @Override
    @Nonnull
    public Map<String, Object> listOptions(List<String> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.emptyMap();
        }

        Map<String, Object> optionMap = listOptions();

        Map<String, Object> result = new HashMap<>(keys.size());

        keys.stream()
                .filter(optionMap::containsKey)
                .forEach(key -> result.put(key, optionMap.get(key)));
        return result;
    }

    @Override
    public <T> T getByPropertyOrDefault(@Nonnull PropertyEnum property, @Nonnull Class<T> propertyType, T defaultValue) {
        Assert.notNull(property, "`property` must not be null");
        return getByProperty(property, propertyType).orElse(defaultValue);
    }

    @Override
    public <T> T getByPropertyOrDefault(@Nonnull PropertyEnum property, @Nonnull Class<T> propertyType) {
        return getByProperty(property, propertyType).orElse(property.defaultValue(propertyType));
    }

    @Override
    public <T> T getByKeyOrDefault(String key, Class<T> valueType, T defaultValue) {
        return getByKey(key, valueType).orElse(defaultValue);
    }

    @Override
    public <T> Optional<T> getByProperty(@Nonnull PropertyEnum property, @Nonnull Class<T> propertyType) {
        return getByProperty(property).map(propertyValue -> PropertyEnum.convertTo(propertyValue.toString(), propertyType));
    }

    @Override
    public Optional<Object> getByProperty(@Nonnull PropertyEnum property) {
        Assert.notNull(property, "`property` must not be null");
        return getByKey(property.getValue());
    }

    @Override
    public <T> Optional<T> getByKey(@Nonnull String key, @Nonnull Class<T> valueType) {
        return getByKey(key).map(value -> PropertyEnum.convertTo(value.toString(), valueType));
    }

    @Override
    @Nonnull
    public Optional<Object> getByKey(@Nonnull String key) {
        Assert.hasText(key, "Option key must not be blank");
        return Optional.ofNullable(listOptions().get(key));
    }

    private void publishOptionUpdatedEvent() {
        OptionCacheUtils.deleteCache(OPTION_VALUE);
        eventPublisher.publishEvent(new OptionUpdatedEvent(this));
    }

    private void setCache(Map<String, Object> values) {
        RepositoryId.cache.put(OPTIONS_KEY_PREFIX + OPTION_VALUE, values);
    }
}
