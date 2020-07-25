package com.hb0730.boot.admin.cache.support.serial.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb0730.boot.admin.cache.exception.BootCacheException;
import com.hb0730.boot.admin.cache.support.serial.AbstractSerializer;
import lombok.Getter;

import javax.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;

/**
 * @author bing_huang
 * @date 2020/07/25 18:04
 * @since V1.0
 */
public class Jackson2JsonStringSerializer extends AbstractSerializer<String> {
    public static final Jackson2JsonStringSerializer JSON_STRING_SERIALIZER = new Jackson2JsonStringSerializer(true);
    @Getter
    private ObjectMapper objectMapper = new ObjectMapper();

    public static int IDENTITY_NUMBER = 0x4A953A81;

    private static final int INIT_BUF_SIZE = 256;

    private static ThreadLocal<WeakReference<ByteArrayOutputStream>> threadLocal =
            ThreadLocal.withInitial(() -> new WeakReference<>(new ByteArrayOutputStream(INIT_BUF_SIZE)));


    public Jackson2JsonStringSerializer(boolean useIdentityNumber) {
        super(useIdentityNumber);
    }

    @Override
    protected String doDeserialize(@Nullable byte[] buffer) throws Exception {
        if (null == buffer || 0 == buffer.length) {
            return null;
        }
        try {
            return this.objectMapper.readValue(buffer, 4, buffer.length - 4, String.class);

        } catch (Exception e) {
            throw new BootCacheException("jackson deserialize error:" + e.getMessage(), e);
        }
    }

    @Nullable
    @Override
    public byte[] serialize(@Nullable String obj) throws Exception {
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
            if (useIdentityNumber) {
                byte[] headerBuffer = new byte[4];
                writeHeader(headerBuffer, IDENTITY_NUMBER);
                bos.writeBytes(headerBuffer);
            }
            this.objectMapper.writeValue(bos, obj);
            bos.flush();
            return bos.toByteArray();
        } catch (Exception e) {
            throw new BootCacheException("jackson serializer error", e);
        }
    }
}
