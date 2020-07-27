package com.hb0730.boot.admin.cache.support.serial;

import com.hb0730.boot.admin.cache.exception.BootCacheException;
import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;
import java.util.Objects;

/**
 * 序列化抽象
 *
 * @author bing_huang
 * @date 2020/07/25 15:35
 * @since V1.0
 */
public abstract class AbstractSerializer implements Serializer {
    @Getter
    protected boolean useIdentityNumber;
    private static final int INIT_BUF_SIZE = 256;
    @Getter
    private final int identityNumber;

    private static ThreadLocal<WeakReference<ByteArrayOutputStream>> threadLocal =
            ThreadLocal.withInitial(() -> new WeakReference<>(new ByteArrayOutputStream(INIT_BUF_SIZE)));

    public AbstractSerializer(boolean useIdentityNumber, int identityNumber) {
        this.useIdentityNumber = useIdentityNumber;
        this.identityNumber = identityNumber;
    }

    @Nullable
    protected abstract byte[] doSerialize(@Nonnull ByteArrayOutputStream outStream, @Nullable Object value) throws Exception;

    @Nullable
    @Override
    public byte[] serialize(@Nullable Object obj) throws Exception {
        if (obj == null) {
            return EMPTY_ARRAY;
        }
        try {
            WeakReference<ByteArrayOutputStream> ref = threadLocal.get();
            ByteArrayOutputStream bos = ref.get();
            if (bos == null) {
                bos = new ByteArrayOutputStream(INIT_BUF_SIZE);
                threadLocal.set(new WeakReference<>(bos));
            }
            try {

                if (useIdentityNumber) {
                    byte[] headerBuffer = new byte[4];
                    writeHeader(headerBuffer, identityNumber);
                    bos.writeBytes(headerBuffer);
                }
                return doSerialize(bos, obj);
            } finally {
                bos.close();
            }
        } catch (Exception e) {
            throw new BootCacheException("serialize error", e);
        }
    }

    protected abstract Object doDeserialize(@Nullable byte[] buffer) throws Exception;


    @Nullable
    @Override
    public Object deserialize(@Nullable byte[] buffer) throws Exception {
        if (buffer == null || 0 == buffer.length) {
            return null;
        }
        try {
            if (useIdentityNumber) {
                GlobalSerializeMap.register();
                int identityNumber = parseHeader(buffer);
                AbstractSerializer serializer = (AbstractSerializer) GlobalSerializeMap.get(identityNumber);
                Objects.requireNonNull(serializer, "no deserialize for identity number:" + identityNumber);
                return serializer.doDeserialize(buffer);
            }
            return doDeserialize(buffer);
        } catch (Exception e) {
            throw new BootCacheException("decode error", e);
        }
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
}
