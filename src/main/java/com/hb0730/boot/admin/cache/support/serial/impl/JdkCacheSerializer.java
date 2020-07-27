package com.hb0730.boot.admin.cache.support.serial.impl;

import com.hb0730.boot.admin.cache.support.serial.AbstractSerializer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;

/**
 * jdk 序列化与反序列化
 *
 * @author bing_huang
 * @date 2020/07/25 15:10
 * @since V1.0
 */
public class JdkCacheSerializer extends AbstractSerializer {
    public static final JdkCacheSerializer INSTANCE = new JdkCacheSerializer(true);

    public static int IDENTITY_NUMBER = 0x4A953A80;

    public JdkCacheSerializer(boolean useIdentityNumber) {
        super(useIdentityNumber, IDENTITY_NUMBER);
    }

    @Override
    protected byte[] doSerialize(@Nonnull ByteArrayOutputStream outStream, Object value) throws Exception {
        if (null == value) {
            return EMPTY_ARRAY;
        }
        ObjectOutputStream oos = new ObjectOutputStream(outStream);
        oos.writeObject(value);
        oos.flush();
        return outStream.toByteArray();
    }

    @Override
    protected Object doDeserialize(@Nullable byte[] buffer) throws Exception {
        if (null == buffer || 0 == buffer.length) {
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
