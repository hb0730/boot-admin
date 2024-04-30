package com.hb0730.base.core;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Data
@EqualsAndHashCode
public class UserInfo implements Serializable {
    private String id;
    private String username;
    private String sysCode;


    public UserInfo(String id, String username, String sysCode) {
        this.id = id;
        this.username = username;
        this.sysCode = sysCode;
    }

    public UserInfo() {
    }
}
