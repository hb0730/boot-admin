package com.hb0730.boot.admin.task.spring;

import java.util.Objects;

/**
 * <p>
 * 任务信息
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class TaskConstant {
    /**
     * <p>
     * bean name
     * </p>
     */
    private String beanName;
    /**
     * <p>
     * method name
     * </p>
     */
    private String methodName;
    /**
     * <p>
     * 所需参数
     * </p>
     */
    private String params;
    /**
     * <p>
     * 表达式
     * </P>
     */
    private String cron;
    /**
     * <p>
     * 任务id
     * </p>
     */
    private String taskId;

    public TaskConstant() {
    }

    public TaskConstant(String beanName, String methodName, String params, String cron, String taskId) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;
        this.cron = cron;
        this.taskId = taskId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskConstant that = (TaskConstant) o;
        return Objects.equals(beanName, that.beanName) &&
                Objects.equals(methodName, that.methodName) &&
                Objects.equals(params, that.params) &&
                Objects.equals(cron, that.cron) &&
                Objects.equals(taskId, that.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beanName, methodName, params, cron, taskId);
    }

    @Override
    public String toString() {
        return "TaskConstant{" +
                "beanName='" + beanName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params='" + params + '\'' +
                ", cron='" + cron + '\'' +
                ", taskId='" + taskId + '\'' +
                '}';
    }
}
