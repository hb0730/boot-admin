package com.hb0730.email.core;

import jakarta.mail.Authenticator;

/**
 * 用户名密码认证
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/12/12
 */
public class JakartaUserPassAuthenticator extends Authenticator {
    private final String username;
    private final String password;

    public JakartaUserPassAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
        return new jakarta.mail.PasswordAuthentication(username, password);
    }
}
