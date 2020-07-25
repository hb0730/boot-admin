package com.hb0730.boot.admin.cache.support.serial.impl;

import com.hb0730.boot.admin.cache.exception.BootCacheException;
import com.hb0730.boot.admin.cache.support.serial.AbstractSerializer;

import javax.annotation.Nullable;
import java.io.*;
import java.lang.ref.WeakReference;

/**
 * jdk 序列化与反序列化
 *
 * @author bing_huang
 * @date 2020/07/25 15:10
 * @since V1.0
 */
public class JdkCacheSerializer extends AbstractSerializer<Object> {
    public static final JdkCacheSerializer INSTANCE = new JdkCacheSerializer(true);

    public static int IDENTITY_NUMBER = 0x4A953A80;

    private static final int INIT_BUF_SIZE = 256;

    private static ThreadLocal<WeakReference<ByteArrayOutputStream>> threadLocal =
            ThreadLocal.withInitial(() -> new WeakReference<>(new ByteArrayOutputStream(INIT_BUF_SIZE)));

    public JdkCacheSerializer(boolean useIdentityNumber) {
        super(useIdentityNumber);
    }

    @Nullable
    @Override
    public byte[] serialize(@Nullable Object value) throws Exception {
        try {
            WeakReference<ByteArrayOutputStream> ref = threadLocal.get();
            ByteArrayOutputStream bos = ref.get();
            if (bos == null) {
                bos = new ByteArrayOutputStream(INIT_BUF_SIZE);
                threadLocal.set(new WeakReference<>(bos));
            }

            try {
                if (useIdentityNumber) {
                    bos.write((IDENTITY_NUMBER >> 24) & 0xFF);
                    bos.write((IDENTITY_NUMBER >> 16) & 0xFF);
                    bos.write((IDENTITY_NUMBER >> 8) & 0xFF);
                    bos.write(IDENTITY_NUMBER & 0xFF);
                }


                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(value);
                oos.flush();
                return bos.toByteArray();
            } finally {
                bos.reset();
            }
        } catch (IOException e) {
            throw new BootCacheException("Java Encode error. " + "msg=" + e.getMessage(), e);
        }
    }

    @Override
    protected Object doDeserialize(@Nullable byte[] buffer) throws Exception {
        if (null == buffer) {
            return null;
        }
        ByteArrayInputStream in;
        if (useIdentityNumber) {
            in = new ByteArrayInputStream(buffer, 4, buffer.length - 4);
        } else {
            in = new ByteArrayInputStream(buffer);
        }
        ObjectInputStream ois = buildObjectInputStream(in);
        return ois.readObject();
    }

    protected ObjectInputStream buildObjectInputStream(ByteArrayInputStream in) throws IOException {
        return new ObjectInputStream(in);
    }

}
