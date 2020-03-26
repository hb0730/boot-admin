package com.hb0730.boot.admin.security.model;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
public class LoginSuccess implements Serializable {
    private String accessToken;

    private LoginUser loginUser;
}
