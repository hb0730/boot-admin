package com.hb0730.boot.admin.exception;

import com.hb0730.boot.admin.utils.MessageUtils;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class UserPasswordNotMatchException extends BaseException {
    public UserPasswordNotMatchException() {
        super(MessageUtils.message("user.password.not.match"));
    }
}
