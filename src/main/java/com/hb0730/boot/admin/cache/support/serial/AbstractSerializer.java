package com.hb0730.boot.admin.cache.support.serial;

import com.hb0730.boot.admin.cache.exception.BootCacheException;
import lombok.Getter;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * @author bing_huang
 * @date 2020/07/25 15:35
 * @since V1.0
 */
public abstract class AbstractSerializer<T> implements Serializer<T> {
    @Getter
    protected boolean useIdentityNumber;

    public AbstractSerializer(boolean useIdentityNumber) {
        this.useIdentityNumber = useIdentityNumber;
    }

    protected void writeHeader(byte[] buf, int header) {
        buf[0] = (byte) (header >> 24 & 0xFF);
        buf[1] = (byte) (header >> 16 & 0xFF);
        buf[2] = (byte) (header >> 8 & 0xFF);
        buf[3] = (byte) (header & 0xFF);
    }

    protected int parseHeader(byte[] buf) {
        int x = 0;
        x = x | (buf[0] & 0xFF);
        x <<= 8;
        x = x | (buf[1] & 0xFF);
        x <<= 8;
        x = x | (buf[2] & 0xFF);
        x <<= 8;
        x = x | (buf[3] & 0xFF);
        return x;
    }

    protected abstract T doDeserialize(@Nullable byte[] buffer) throws Exception;

    @Nullable
    @Override
    public T deserialize(@Nullable byte[] buffer) throws Exception {
        if (buffer == null) {
            return null;
        }
        try {
            if (useIdentityNumber) {
                GlobalSerializeMap.register();
                int identityNumber = parseHeader(buffer);
                Serializer<?> serializer = GlobalSerializeMap.get(identityNumber);
                Objects.requireNonNull(serializer, "no deserialize for identity number:" + identityNumber);
                return doDeserialize(buffer);
            } else {
                return doDeserialize(buffer);
            }
        } catch (Exception e) {
            throw new BootCacheException("decode error", e);
        }
    }
}
