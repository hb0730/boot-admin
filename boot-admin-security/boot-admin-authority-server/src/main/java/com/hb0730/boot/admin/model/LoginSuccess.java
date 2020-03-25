package com.hb0730.boot.admin.model;

import com.hb0730.boot.admin.commons.web.security.model.LoginUser;
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
