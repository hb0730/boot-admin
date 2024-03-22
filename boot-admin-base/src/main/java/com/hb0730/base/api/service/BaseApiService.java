package com.hb0730.base.api.service;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.hb0730.base.api.ApiAuth;
import com.hb0730.base.api.Pair;
import com.hb0730.base.api.TaskStatus;
import com.hb0730.base.utils.BootAdminUtil;
import com.hb0730.base.utils.JsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 对外接口基础实现
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/1/23
 */
public abstract class BaseApiService implements Api {
    @Override
    public String getName() {
        return AnnotationUtil.getAnnotation(this.getClass(), ApiService.class).name();
    }

    @Override
    public String getChannel() {
        return AnnotationUtil.getAnnotation(this.getClass(), ApiService.class).channel();
    }

    @Override
    public String getGroup() {
        return AnnotationUtil.getAnnotation(this.getClass(), ApiService.class).group();
    }

    @Override
    public TaskStatus execute(ApiAuth auth, String params) {
        @SuppressWarnings("unchecked")
        Map<String, Object> paramsMap = JsonUtil.DEFAULT.json2Obj(params, Map.class);
        if (StrUtil.isNotBlank(params) && null == paramsMap) {
            return TaskStatus.WARNING().setMessage("非法的业务参数");
        }
        return execute(auth, paramsMap);
    }

    /**
     * 业务处理
     *
     * @param auth      授权信息
     * @param bizParams 接口参数
     * @return 处理结果
     */
    public TaskStatus execute(ApiAuth auth, Map<String, Object> bizParams) {
        //参数通用验证
        List<Pair<String, String>> invalidParams = checkParams(bizParams, getParamRule(), getSpecialRule());
        if (CollectionUtil.isNotEmpty(invalidParams)) {
            String json = JsonUtil.DEFAULT.toJson(
                    invalidParams.stream().map(
                            Pair::getMessage
                    ).collect(
                            Collectors.toList()
                    )
            );
            return TaskStatus.WARNING().setMessage(json).setResult(invalidParams);

        }
        return doExecute(auth, bizParams);
    }

    /**
     * 业务处理
     *
     * @param auth   授权信息
     * @param params 接口参数
     * @return 处理结果
     */
    protected TaskStatus doExecute(ApiAuth auth, Map<String, Object> params) {
        return doExecute(params);
    }


    /**
     * 业务处理
     *
     * @param params 接口参数
     * @return 处理结果
     */
    protected abstract TaskStatus doExecute(Map<String, Object> params);

    /**
     * 参数要求
     */
    public enum ParamCheck {
        /* 必须 */
        REQUIRED,
        /* 必须提供一个：需另外配置参数名称 */
        REQUIREDONE,
        /* 日期格式（yyyy-MM-dd） */
        DATE,
        /* 时间段 格式：10:00~11:59 */
        TIME_PERIOD,
        /* 日期格式（yyyyMMdd） */
        DATE_yyyyMMdd,
        /* 日期时间格式（yyyy-MM-dd HH:mm:ss） */
        DATETIME,
        /* 日期时间格式 （yyyyMMddHHmmss）*/
        DATETIME_yyyyMMddHHmmss,
        /* 整数 */
        INTEGER,
        /* 数值 */
        NUMERIC,
        /* 特定值：需另外配置可选值 */
        SPECIAL,
        /* 正则表达式：需另外配置参数名称 */
        REGULAR,
        /* 文字字数限制：需另外配置限制长度 */
        LENGTH,
        /* 合法手机*/
        PHONE,
        /* 无要求 */
        NONE
    }

    /**
     * 参数基本要求
     */
    protected abstract Map<String, Pair<String, ParamCheck[]>> getParamRule();

    /**
     * 参数特殊要求
     */
    protected abstract Map<String, String[]> getSpecialRule();

    /**
     * 接口参数验证
     *
     * @param paramsMap   接口参数
     * @param paramsRule  参数要求
     * @param specialRule 特殊要求
     * @return 错误信息
     */
    protected List<Pair<String, String>> checkParams(Map<String, ?> paramsMap,
                                                     Map<String, Pair<String, ParamCheck[]>> paramsRule,
                                                     Map<String, String[]> specialRule) {
        if (null == paramsMap || paramsMap.isEmpty()) {
            return null;
        }
        if (null == specialRule) {
            specialRule = Collections.emptyMap();
        }

        //检查结果
        List<Pair<String, String>> subErrors = new ArrayList<>();

        //检查必填参数
        for (String paramName : paramsRule.keySet()) {
            String value = MapUtil.get(paramsMap, paramName, String.class);

            Pair<String, ParamCheck[]> rules = paramsRule.get(paramName);
            for (ParamCheck paramCheck : rules.getMessage()) {
                switch (paramCheck) {
                    case REQUIRED:
                        if (StrUtil.isBlank(value)) {
                            subErrors.add(new Pair<>("isv.missing-parameter:{}", String.format("缺少必填参数:%s(%s)",
                                    rules.getCode(), paramName)));
                        }
                        break;
                    case REQUIREDONE:
                        if (StrUtil.isBlank(value)) {
                            boolean hasValue = false;
                            String[] otherParams = specialRule.get(paramName);
                            for (String otherParam : otherParams) {
                                String otherValue = MapUtil.get(paramsMap, otherParam, String.class);
                                if (StrUtil.isNotBlank(otherValue)) {
                                    hasValue = true;
                                    break;
                                }
                            }
                            if (!hasValue) {
                                subErrors.add(new Pair<>("isv.missing-parameter:{}", String.format("%s或（%s）必须提供一项！",
                                        paramName, StrUtil.join(",", Arrays.asList(otherParams)))));
                            }
                        }
                        break;
                    case DATE:
                        if (StrUtil.isNotBlank(value) && null == BootAdminUtil.string2Date(value)) {

                            subErrors.add(new Pair<>("isv.invalid-parameter:{}", String.format("参数值不是有效的日期类型:%s(%s)" +
                                            "[%s]",
                                    rules.getCode(), paramName, value)));
                        }
                        break;
                    case TIME_PERIOD:
                        if (StrUtil.isNotBlank(value) && !BootAdminUtil.timePeriod(value)) {
                            subErrors.add(new Pair<>("isv.invalid-parameter:{}", String.format("参数值不是有效的时间段类型:%s(%s)" +
                                            "[%s]",
                                    rules.getCode(), paramName, value)));
                        }
                        break;

                    case DATE_yyyyMMdd:
                        if (StrUtil.isNotBlank(value) && null == BootAdminUtil.string2Date(value, BootAdminUtil.DATE_yyyyMMdd_FORMAT)) {
                            subErrors.add(new Pair<>("isv.invalid-parameter:{}", String.format("参数值不是有效的日期类型:%s(%s)" +
                                            "[%s]",
                                    rules.getCode(), paramName, value)));
                        }
                        break;
                    case DATETIME:
                        if (StrUtil.isNotBlank(value) && null == BootAdminUtil.string2Date(value, BootAdminUtil.DATE_TIME_FORMAT)) {
                            subErrors.add(new Pair<>("isv.invalid-parameter:{}", String.format("参数值不是有效的日期时间类型:%s(%s)" +
                                            "[%s]",
                                    rules.getCode(), paramName, value)));
                        }
                        break;
                    case DATETIME_yyyyMMddHHmmss:
                        if (StrUtil.isNotBlank(value) && null == BootAdminUtil.string2Date(value, BootAdminUtil.DATE_yyyyMMddHHmmss_FORMAT)) {
                            subErrors.add(new Pair<>("isv.invalid-parameter:{}", String.format("参数值不是有效的日期时间类型:%s(%s)" +
                                            "[%s]",
                                    rules.getCode(), paramName, value)));
                        }
                        break;
                    case INTEGER:
                        if (StrUtil.isNotBlank(value) && null == BootAdminUtil.string2Integer(value)) {
                            subErrors.add(new Pair<>("isv.invalid-parameter:{}", String.format("参数值不是有效的整数类型:%s(%s)" +
                                            "[%s]",
                                    rules.getCode(), paramName, value)));
                        }
                        break;
                    case NUMERIC:
                        if (StrUtil.isNotBlank(value) && !StrUtil.isNumeric(value)) {
                            subErrors.add(new Pair<>("isv.invalid-parameter:{}", String.format("参数值不是有效的数值类型:%s(%s)" +
                                            "[%s]",
                                    rules.getCode(), paramName, value)));
                        }
                        break;
                    case SPECIAL:
                        if (StrUtil.isBlank(value)) {
                            break;
                        }
                        String[] specialValues = specialRule.get(paramName);
                        if (specialValues != null && specialValues.length > 0) {
                            if (SPECIAL_KEY_FLAG.equals(specialValues[0])) {
                                specialValues = getSpecialValues(specialValues[1]);
                                if (specialValues == null || specialValues.length == 0) {
                                    break;
                                }
                            }
                            if (!Arrays.asList(specialValues).contains(value)) {
                                subErrors.add(new Pair<>("isv.invalid-parameter", String.format("%s(%s)[%s]非法的参数值！",
                                        rules.getCode(), paramName, value)));
                            }
                        }
                        break;
                    case REGULAR:
                        if (StrUtil.isBlank(value)) {
                            break;
                        }
                        String[] regular = specialRule.get(paramName + "_regular");
                        if (regular != null) {
                            if (!value.matches(regular[0])) {
                                subErrors.add(new Pair<>("isv.invalid-parameter", String.format("%s(%s)[%s]存在非法字符," +
                                        "只能使用字母/数字/中线/下划线！", rules.getCode(), paramName, value)));
                            }
                        }
                        break;
                    case LENGTH:
                        String[] lengthTemp = specialRule.get(paramName + "_length");
                        if (lengthTemp != null) {
                            Integer length = BootAdminUtil.string2Integer(lengthTemp[0]);
                            if (StrUtil.isNotBlank(value) && null != length && value.length() > length) {
                                subErrors.add(new Pair<>("isv.invalid-parameter", String.format("%s(%s)[%s]字数过长," +
                                        "请保持在%s字以内！", rules.getCode(), paramName, value, length)));
                            }
                        }
                        break;
                    case PHONE:
                        if (StrUtil.isNotBlank(value) && !BootAdminUtil.isValidMobile(value)) {
                            subErrors.add(new Pair<>("isv.invalid-parameter", String.format("%s(%s)[%s]不是合法的手机号码！",
                                    rules.getCode(), paramName, value)));
                        }
                        break;
                    case NONE:
                    default:
                        break;
                }
            }

        }
        return subErrors;
    }

    /**
     * 配置KEY标志
     */
    protected final static String SPECIAL_KEY_FLAG = "`SPECIAL_KEY_FLAG`";

    /**
     * 根据配置KEY取得配置值
     *
     * @param key 配置KEY
     * @return 配置值
     */
    protected String[] getSpecialValues(String key) {
        return null;
    }
}

