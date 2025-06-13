package com.hb0730.email.core;

import cn.hutool.core.lang.RegexPool;
import cn.hutool.setting.Setting;
import com.hb0730.base.pool.StrPool;
import com.hb0730.base.utils.RegexUtil;
import com.hb0730.base.utils.StrUtil;
import lombok.Getter;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 邮箱账户
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/12/12
 */
@Getter
public class MailAccount implements Serializable {
    // ----------------------------------------------------------------------- Static

    // 邮件协议
    private static final String MAIL_PROTOCOL = "mail.transport.protocol";
    // 邮件服务器主机
    private static final String SMTP_HOST = "mail.%s.host";
    // 邮件服务器端口
    private static final String SMTP_PORT = "mail.%s.port";
    // 是否需要验证
    private static final String SMTP_AUTH = "mail.%s.auth";
    // 发送方
    private static final String SMTP_FROM = "mail.%s.from";
    // 用户名
    private static final String SMTP_USER = "mail.%s.user";
    // 密码
    private static final String SMTP_PASS = "mail.%s.pass";
    // 超时
    private static final String SMTP_TIMEOUT = "mail.%s.timeout";
    // 连接超时
    private static final String SMTP_CONNECTION_TIMEOUT = "mail.%s.connectiontimeout";
    // 写超时
    private static final String SMTP_WRITE_TIMEOUT = "mail.%s.writetimeout";

    // 使用 STARTTLS安全连接
    private static final String STARTTLS_ENABLE = "mail.%s.starttls.enable";
    // 使用 SSL安全连接
    private static final String SSL_ENABLE = "mail.%s.ssl.enable";
    // SSL协议
    private static final String SSL_PROTOCOLS = "mail.%s.ssl.protocols";
    // 指定实现javax.net.SocketFactory接口的类的名称
    private static final String SOCKET_FACTORY_CLASS = "mail.%s.socketFactory.class";
    // 如果设置为true,未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类
    private static final String SOCKET_FACTORY_FALLBACK = "mail.%s.socketFactory.fallback";
    // 指定的端口连接到在使用指定的套接字工厂
    private static final String SOCKET_FACTORY_PORT = "mail.%s.socketFactory.port";


    // 是否开启debug模式
    private static final String MAIL_DEBUG = "mail.debug";
    // 编码
    private static final String MAIL_CHARSET = "mail.charset";
    // 对于超长参数是否切分为多份
    private static final String MAIL_SPLITLONGPARAMETERS = "mail.mime.splitlongparameters";
    // 对于文件名是否使用charset编码
    private static final String MAIL_ENCODEFILENAME = "mail.mime.encodefilename";


    // ----------------------------------------------------------------------- Fields
    /**
     * SMTP服务器域名
     */
    private String host;
    /**
     * SMTP服务端口
     */
    private Integer port;
    /**
     * 协议，默认smtp
     */
    private String protocol = "smtp";
    /**
     * 是否需要用户名密码验证
     */
    private Boolean auth;
    /**
     * 用户名
     */
    private String user;
    /**
     * 密码
     */
    private String pass;
    /**
     * 发送方，遵循RFC-822标准
     */
    private String from;

    /**
     * 是否开启debug模式,默认关闭
     */
    private Boolean debug = false;
    /**
     * 编码用于编码邮件正文和发送人、收件人等中文
     */
    private Charset charset = StrPool.CHARSET_UTF_8;
    /**
     * 对于超长参数是否切分为多份，默认为false（国内邮箱附件不支持切分的附件名）
     */
    private boolean splitlongparameters = false;
    /**
     * 对于文件名是否使用{@link #charset}编码，默认为 {@code true}
     */
    private boolean encodefilename = true;

    /**
     * 使用 STARTTLS安全连接，STARTTLS是对纯文本通信协议的扩展。它将纯文本连接升级为加密连接（TLS或SSL）， 而不是使用一个单独的加密通信端口。
     */
    private boolean starttlsEnable = false;
    /**
     * 使用 SSL安全连接
     */
    private Boolean sslEnable;

    /**
     * SSL协议，多个协议用空格分隔
     */
    private String sslProtocols;

    /**
     * 指定实现javax.net.SocketFactory接口的类的名称,这个类将被用于创建SMTP的套接字
     */
    private String socketFactoryClass = "javax.net.ssl.SSLSocketFactory";
    /**
     * 如果设置为true,未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类, 默认值为true
     */
    private boolean socketFactoryFallback;
    /**
     * 指定的端口连接到在使用指定的套接字工厂。如果没有设置,将使用默认端口
     */
    private int socketFactoryPort = 465;

    /**
     * SMTP超时时长，单位毫秒，缺省值不超时
     */
    private long timeout;
    /**
     * Socket连接超时值，单位毫秒，缺省值不超时
     */
    private long connectionTimeout;
    /**
     * Socket写出超时值，单位毫秒，缺省值不超时
     */
    private long writeTimeout;

    /**
     * 自定义的其他属性，此自定义属性会覆盖默认属性
     */
    private final Map<String, Object> customProperty = new HashMap<>();

    /**
     * 构造,所有参数需自行定义或保持默认值
     */
    public MailAccount() {
    }

    /**
     * 构造
     *
     * @param settingPath 配置文件路径
     */
    public MailAccount(String settingPath) {
        this(new Setting(settingPath));
    }

    /**
     * 构造
     *
     * @param setting 配置文件
     */
    public MailAccount(Setting setting) {
        setting.toBean(this);

        // since 5.8.30, custom property
        setting.forEach((key, value) -> {
            if (StrUtil.startWith(key, "mail.")) {
                this.setCustomProperty(key, value);
            }
        });
    }

    /**
     * 设置SMTP服务器域名
     *
     * @param host SMTP服务器域名
     * @return this
     */
    public MailAccount setHost(String host) {
        this.host = host;
        return this;
    }

    /**
     * 设置SMTP服务端口
     *
     * @param port SMTP服务端口
     * @return this
     */
    public MailAccount setPort(Integer port) {
        this.port = port;
        return this;
    }

    /**
     * 设置协议
     *
     * @param protocol 协议
     * @return this
     */
    public MailAccount setProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    /**
     * 设置是否需要用户名密码验证
     *
     * @param auth 是否需要用户名密码验证
     * @return this
     */
    public MailAccount setAuth(boolean auth) {
        this.auth = auth;
        return this;
    }

    /**
     * 是否需要用户名密码验证
     *
     * @return 是否需要用户名密码验证
     */
    public boolean isAuth() {
        return auth;
    }


    /**
     * 设置用户名
     *
     * @param user 用户名
     * @return this
     */
    public MailAccount setUser(String user) {
        this.user = user;
        return this;
    }

    /**
     * 设置密码
     *
     * @param pass 密码
     * @return this
     */
    public MailAccount setPass(String pass) {
        this.pass = pass;
        return this;
    }

    /**
     * 设置发送方，遵循RFC-822标准 "Personal Name &lt;user@host.domain&gt;" or user@host.domain
     *
     * @param from 发送方
     * @return this
     */
    public MailAccount setFrom(String from) {
        this.from = from;
        return this;
    }

    /**
     * 设置是否开启debug模式
     *
     * @param debug 是否开启debug模式
     * @return this
     */
    public MailAccount setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    /**
     * 设置编码
     *
     * @param charset 编码
     * @return this
     */
    public MailAccount setCharset(Charset charset) {
        this.charset = charset;
        return this;
    }

    /**
     * 设置对于超长参数是否切分为多份
     *
     * @param splitlongparameters 对于超长参数是否切分为多份
     * @return this
     */
    public MailAccount setSplitlongparameters(boolean splitlongparameters) {
        this.splitlongparameters = splitlongparameters;
        return this;
    }

    /**
     * 设置对于文件名是否使用charset编码
     *
     * @param encodefilename 对于文件名是否使用charset编码
     * @return this
     */
    public MailAccount setEncodefilename(boolean encodefilename) {
        this.encodefilename = encodefilename;
        return this;
    }

    /**
     * 设置使用 STARTTLS安全连接
     *
     * @param starttlsEnable 使用 STARTTLS安全连接
     * @return this
     */
    public MailAccount setStarttlsEnable(boolean starttlsEnable) {
        this.starttlsEnable = starttlsEnable;
        return this;
    }

    /**
     * 设置使用 SSL安全连接
     *
     * @param sslEnable 使用 SSL安全连接
     * @return this
     */
    public MailAccount setSslEnable(Boolean sslEnable) {
        this.sslEnable = sslEnable;
        return this;
    }

    /**
     * 设置SSL协议
     *
     * @param sslProtocols SSL协议
     * @return this
     */
    public MailAccount setSslProtocols(String sslProtocols) {
        this.sslProtocols = sslProtocols;
        return this;
    }

    /**
     * 设置指定实现javax.net.SocketFactory接口的类的名称
     *
     * @param socketFactoryClass 指定实现javax.net.SocketFactory接口的类的名称
     * @return this
     */
    public MailAccount setSocketFactoryClass(String socketFactoryClass) {
        this.socketFactoryClass = socketFactoryClass;
        return this;
    }


    /**
     * 设置如果设置为true,未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类
     *
     * @param socketFactoryFallback 如果设置为true,未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类
     * @return this
     */
    public MailAccount setSocketFactoryFallback(boolean socketFactoryFallback) {
        this.socketFactoryFallback = socketFactoryFallback;
        return this;
    }

    /**
     * 设置指定的端口连接到在使用指定的套接字工厂
     *
     * @param socketFactoryPort 指定的端口连接到在使用指定的套接字工厂
     * @return this
     */
    public MailAccount setSocketFactoryPort(int socketFactoryPort) {
        this.socketFactoryPort = socketFactoryPort;
        return this;
    }

    /**
     * 设置SMTP超时时长，单位毫秒，缺省值不超时
     *
     * @param timeout SMTP超时时长，单位毫秒，缺省值不超时
     * @return this
     */
    public MailAccount setTimeout(long timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * 设置Socket连接超时值，单位毫秒，缺省值不超时
     *
     * @param connectionTimeout Socket连接超时值，单位毫秒，缺省值不超时
     * @return this
     */
    public MailAccount setConnectionTimeout(long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
    }

    /**
     * 设置Socket写出超时值，单位毫秒，缺省值不超时
     *
     * @param writeTimeout Socket写出超时值，单位毫秒，缺省值不超时
     * @return this
     */
    public MailAccount setWriteTimeout(long writeTimeout) {
        this.writeTimeout = writeTimeout;
        return this;
    }

    /**
     * 设置自定义的其他属性，此自定义属性会覆盖默认属性
     *
     * @param customProperty 自定义的其他属性
     * @return this
     */
    public MailAccount setCustomProperty(Map<String, Object> customProperty) {
        this.customProperty.clear();
        this.customProperty.putAll(customProperty);
        return this;
    }


    /**
     * 设置自定义的其他属性，此自定义属性会覆盖默认属性
     *
     * @param key   属性名
     * @param value 属性值
     * @return this
     */
    public MailAccount setCustomProperty(String key, Object value) {
        this.customProperty.put(key, value);
        return this;
    }

    /**
     * 设置自定义的其他属性，此自定义属性会覆盖默认属性
     *
     * @param key   属性名
     * @param value 属性值
     * @return this
     */
    public MailAccount setCustomProperty(String key, String value) {
        this.customProperty.put(key, value);
        return this;
    }

    /**
     * 获取SMTP配置信息
     *
     * @return 配置信息
     */
    public Properties getProperties() {
        //全局系统参数
        System.setProperty(MAIL_SPLITLONGPARAMETERS, String.valueOf(this.splitlongparameters));

        final Properties props = new Properties();
        props.setProperty(MAIL_PROTOCOL, this.protocol);
        props.setProperty(getVal(SMTP_HOST, this.protocol), this.host);
        props.setProperty(getVal(SMTP_PORT, this.protocol), String.valueOf(this.port));
        props.setProperty(getVal(SMTP_AUTH, this.protocol), String.valueOf(this.auth));
        if (this.timeout > 0) {
            props.setProperty(getVal(SMTP_TIMEOUT, this.protocol), String.valueOf(this.timeout));
        }
        if (this.connectionTimeout > 0) {
            props.setProperty(getVal(SMTP_CONNECTION_TIMEOUT, this.protocol), String.valueOf(this.connectionTimeout));
        }
        if (this.writeTimeout > 0) {
            props.setProperty(getVal(SMTP_WRITE_TIMEOUT, this.protocol), String.valueOf(this.writeTimeout));
        }
        props.setProperty(MAIL_DEBUG, String.valueOf(this.debug));

        if (this.starttlsEnable) {
            //STARTTLS是对纯文本通信协议的扩展。它将纯文本连接升级为加密连接（TLS或SSL）， 而不是使用一个单独的加密通信端口。
            props.put(getVal(STARTTLS_ENABLE, this.protocol), "true");

            if (null == this.sslEnable) {
                //为了兼容旧版本，当用户没有此项配置时，按照starttlsEnable开启状态时对待
                this.sslEnable = true;
            }
        }

        if (null != this.sslEnable && this.sslEnable) {
            props.put(getVal(SSL_ENABLE, this.protocol), "true");
            props.put(getVal(SOCKET_FACTORY_CLASS, this.protocol), this.socketFactoryClass);
            props.put(getVal(SOCKET_FACTORY_FALLBACK, this.protocol), String.valueOf(this.socketFactoryFallback));
            props.put(getVal(SOCKET_FACTORY_PORT, this.protocol), String.valueOf(this.socketFactoryPort));
            if (StrUtil.isNotBlank(this.sslProtocols)) {
                props.put(getVal(SSL_PROTOCOLS, this.protocol), this.sslProtocols);
            }
        }

        // 补充自定义属性，允许自定属性覆盖已经设置的值
        props.putAll(this.customProperty);

        return props;
    }


    /**
     * 如果某些值为null，使用默认值
     *
     * @return 配置信息
     */
    public MailAccount defaultIfEmpty() {
        // 发件人邮箱, 提取邮箱地址
        // from "Personal Name %lt;user@host.domain%gt;" > user@host.domain
        // form "user@host.domain" > user@host.domain
        final String fromAddress = RegexUtil.get(RegexPool.EMAIL, this.from, 0);

        if (StrUtil.isBlank(this.host)) {
            // 如果SMTP地址为空，默认使用smtp.<发件人邮箱后缀>
            this.host = StrUtil.format("smtp.{}", StrUtil.subSuf(fromAddress, fromAddress.indexOf('@') + 1));
        }

        if (StrUtil.isBlank(this.user)) {
            // 如果用户名为空，默认为发件人邮箱
            this.user = fromAddress;
        }

        if (null == this.auth) {
            // 如果密码非空白，则使用认证模式
            this.auth = StrUtil.isNotBlank(this.pass);
        }

        if (null == this.port) {
            // 端口在SSL状态下默认与socketFactoryPort一致，非SSL状态下默认为25
            this.port = (null != this.sslEnable && this.sslEnable) ? this.socketFactoryPort : 25;
        }
        if (null == this.charset) {
            // 默认UTF-8编码
            this.charset = StrPool.CHARSET_UTF_8;
        }
        return this;
    }


    /**
     * 格式化配置信息
     *
     * @param key      key
     * @param protocol 协议
     * @return 格式化后的key
     */
    private String getVal(String key, String protocol) {
        return String.format(key, protocol);
    }
}
