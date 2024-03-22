package com.hb0730.base.api;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 业务处理结果
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/1/23
 */
@Getter
@Setter
public class TaskStatus implements Serializable {
    /**
     * 处理成功
     */
    public static TaskStatus SUCCESS() {
        return get(State.OK);
    }

    /**
     * 处理失败
     */
    public static TaskStatus WARNING() {
        return get(State.NG);
    }

    /**
     * 生成指定类型的业务处理结果
     *
     * @param state 处理结果枚举
     * @return 业务处理结果
     */
    private static TaskStatus get(State state) {
        return new TaskStatus(state);
    }
    //--------------------------------------------------------------------------

    /**
     * 处理结果
     */
    public TaskStatus() {
        this.state = State.OK;
        this.status = new Pair<Integer, String>(200, "处理成功");
    }

    /**
     * 处理结果
     *
     * @param state 成功/失败
     */
    public TaskStatus(State state) {
        this.state = state;
        if (this.state == State.OK) {
            this.status = new Pair<Integer, String>(200, "处理成功");
        } else {
            this.status = new Pair<Integer, String>(500, "处理失败");
        }
    }

    /**
     * 处理结果
     *
     * @param state  成功/失败
     * @param status 处理结果消息KEY
     */
    public TaskStatus(State state, Pair<Integer, String> status) {
        this.state = state;
        this.status = status;
    }
    //--------------------------------------------------------------------------

    /**
     * 处理结果枚举
     */
    public enum State {
        /**
         * 成功
         */
        NG,
        /**
         * 失败
         */
        OK
    }
    //--------------------------------------------------------------------------
    /**
     * 处理结果
     */
    private State state;
    /**
     * 处理结果详细信息
     */
    private final Pair<Integer, String> status;
    /**
     * 附加数据
     */
    private Object result;

    /**
     * 是否成功处理
     *
     * @return 是否成功处理
     */
    @JsonIgnore
    public boolean isSuccess() {
        return this.status.getCode() == 200;
    }

    /**
     * 处理结果
     *
     * @return 处理结果
     */
    @JsonIgnore
    public State getState() {
        return state;
    }


    /**
     * 设置处理结果代码
     *
     * @param code 处理结果代码
     * @return 处理结果详细信息
     */
    public TaskStatus setCode(Integer code) {
        this.status.setCode(code);
        return this;
    }

    /**
     * 取得处理结果代码
     *
     * @return 处理结果代码
     */
    public Integer getCode() {
        return this.status.getCode();
    }

    /**
     * 设置处理结果消息
     *
     * @param message 处理结果消息
     * @return 处理结果详细信息
     */
    public TaskStatus setMessage(String message) {
        this.status.setMessage(message);
        return this;
    }

    /**
     * 取得处理结果消息
     *
     * @return 处理结果消息
     */
    public String getMessage() {
        return StrUtil.isBlank(this.status.getMessage()) ? "处理成功！" : this.status.getMessage();
    }

    /**
     * 取得附加数据
     *
     * @return 附加数据
     */
    public TaskStatus setResult(Object result) {
        this.result = result;
        return this;
    }
}
