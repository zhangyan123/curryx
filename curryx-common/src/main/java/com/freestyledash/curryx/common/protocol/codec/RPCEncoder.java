package com.freestyledash.curryx.common.protocol.codec;

import com.freestyledash.curryx.common.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RPCEncoder extends MessageToByteEncoder {

    private static final Logger logger = LoggerFactory.getLogger(RPCEncoder.class);

    /**
     * 要编码的对象类型
     */
    private Class<?> clazz;

    public RPCEncoder(Class<?> clazz) {
        this.clazz = clazz;
    }

    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        if (clazz.isInstance(o)) {
            byte[] buffer = SerializationUtil.serialize(o);
            byteBuf.writeInt(buffer.length);
            byteBuf.writeBytes(buffer);

            logger.debug("将({})类型的对象编码为长度为{}的字节数组", clazz.getName(), buffer.length);
        } else {
            throw new IllegalStateException("非法的编码请求：不兼容的编码类型" + clazz);
        }
    }
}
