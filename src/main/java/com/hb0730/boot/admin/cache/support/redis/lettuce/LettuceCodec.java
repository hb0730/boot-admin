package com.hb0730.boot.admin.cache.support.redis.lettuce;

import io.lettuce.core.codec.RedisCodec;

import java.nio.ByteBuffer;

/**
 * @author bing_huang
 * @date 2020/07/22 9:51
 * @since V1.0
 */
public class LettuceCodec implements RedisCodec<byte[], byte[]> {
    @Override
    public byte[] decodeKey(ByteBuffer byteBuffer) {
        return convert(byteBuffer);
    }

    @Override
    public byte[] decodeValue(ByteBuffer byteBuffer) {
        return convert(byteBuffer);
    }

    @Override
    public ByteBuffer encodeKey(byte[] bytes) {
        return ByteBuffer.wrap(bytes);
    }

    @Override
    public ByteBuffer encodeValue(byte[] bytes) {
        return ByteBuffer.wrap(bytes);
    }

    private byte[] convert(ByteBuffer byb) {
        byte[] bs = new byte[byb.remaining()];
        byb.get(bs);
        return bs;
    }
}
