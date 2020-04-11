package com.hb0730.boot.admin.security.handle;

import com.hb0730.boot.admin.commons.constant.enums.TokenTypeEnum;
import com.hb0730.boot.admin.security.service.ITokenService;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.LinkedList;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class TokenHandlers {
    /**
     * File handler container.
     */
    private final Collection<ITokenService> tokenHandlers = new LinkedList<>();

    public TokenHandlers(ApplicationContext applicationContext) {
        // Add all file handler
        addFileHandlers(applicationContext.getBeansOfType(ITokenService.class).values());
    }

    /**
     * <p>
     * 获取对应的token
     * </p>
     *
     * @param typeEnum token类型
     * @return 对象的token实现类
     */
    public ITokenService getImpl(TokenTypeEnum typeEnum) {
        for (ITokenService tokenHandler : tokenHandlers) {
            if (tokenHandler.supportType(typeEnum)) {
                return tokenHandler;
            }
        }
        return null;
    }

    /**
     * Adds file handlers.
     *
     * @param fileHandlers file handler collection
     * @return current file handlers
     */
    @NonNull
    public TokenHandlers addFileHandlers(@Nullable Collection<ITokenService> fileHandlers) {
        if (!CollectionUtils.isEmpty(fileHandlers)) {
            this.tokenHandlers.addAll(fileHandlers);
        }
        return this;
    }
}
