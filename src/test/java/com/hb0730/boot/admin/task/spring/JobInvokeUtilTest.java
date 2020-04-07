package com.hb0730.boot.admin.task.spring;

import com.hb0730.boot.admin.commons.utils.spring.SpringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class JobInvokeUtilTest {

    @Test
    public void invokeMethod() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        TaskConstant taskConstant=new TaskConstant();
        taskConstant.setMethodName("params");
        taskConstant.setBeanName("taskTest");
        taskConstant.setParams("{\"java.lang.String\":\"test\"}");
        JobInvokeUtil.invokeMethod(taskConstant);
        taskConstant.setMethodName("multipleParams");
        taskConstant.setParams("{\"java.lang.String\":\"test\",\"java.lang.Boolean\":\"true\",\"java.lang.Long\":\"123L\",\"java.lang.Double\":\"123.03D\",\"java.lang.Integer\":\"123\"}");
        JobInvokeUtil.invokeMethod(taskConstant);
        taskConstant.setMethodName("obj");
        taskConstant.setParams("{\"com.hb0730.boot.admin.project.monitor.job.handler.Test1\":{\"java.lang.Integer\":\"1\"}}");
        JobInvokeUtil.invokeMethod(taskConstant);
    }

    @Test
    public void validClassName() throws ClassNotFoundException {
//        String className="sss.sss.ttt.Test";
//        Class.forName(className);
        SpringUtils.containsBean("com.hb0730.boot.admin.project.monitor.job.handler.TaskTest");
    }
}
