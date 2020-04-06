package com.hb0730.boot.admin.task.spring;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class JobInvokeUtilTest {

    @Test
    public void invokeMethod() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        TaskConstant taskConstant=new TaskConstant();
        taskConstant.setMethodName("params");
        taskConstant.setBeanName("com.hb0730.boot.admin.project.monitor.job.handler.TaskTest");
        taskConstant.setParams("{\"java.lang.String\":\"test\"}");
        JobInvokeUtil.invokeMethod(taskConstant);
        taskConstant.setMethodName("multipleParams");
        taskConstant.setParams("{\"java.lang.String\":\"test\",\"java.lang.Boolean\":\"true\",\"java.lang.Long\":\"123L\",\"java.lang.Double\":\"123.03D\",\"java.lang.Integer\":\"123\"}");
        JobInvokeUtil.invokeMethod(taskConstant);
        taskConstant.setMethodName("obj");
        taskConstant.setParams("{\"com.hb0730.boot.admin.project.monitor.job.handler.Test1\":{\"java.lang.Integer\":\"1\"}}");
        JobInvokeUtil.invokeMethod(taskConstant);
    }
}
