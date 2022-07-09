package com.hb0730.boot.admin.message;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/9
 */
public interface BootAdminMsg {
    /**
     * 发送消息
     *
     * @param receiver 消息接收人
     * @param title    消息标题
     * @param content  消息内容
     */
    void sendMsg(String receiver, String title, String content);
}
