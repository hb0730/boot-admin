package com.hb0730.email.core;


/**
 * 全局邮件帐户
 *
 * @author looly
 */
public enum GlobalMailAccount {
    INSTANCE;

    private MailAccount mailAccount;

    /**
     * 构造
     */
    GlobalMailAccount() {
    }


    /**
     * 设置邮件帐户
     *
     * @param account 邮件帐户
     */
    public void setAccount(MailAccount account) {
        this.mailAccount = account;
    }

    /**
     * 获得邮件帐户
     *
     * @return 邮件帐户
     */
    public MailAccount getAccount() {
        return this.mailAccount;
    }

}
