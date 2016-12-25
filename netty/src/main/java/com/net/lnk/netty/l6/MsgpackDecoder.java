package com.net.lnk.netty.l6;

import java.util.List;

import org.msgpack.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {

	protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
		final int length = buffer.readableBytes();
		final byte[] bytes = new byte[length];
		buffer.getBytes(buffer.readerIndex(), bytes, 0, length);
		MessagePack pack = new MessagePack();
		out.add(pack.read(bytes));
	}

}
