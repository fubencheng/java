package com.net.lnk.netty.l6;

import java.util.ArrayList;
import java.util.List;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {

	private final String host;
	private final int port;

	public EchoClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public static void main(String[] args) {
		int port = 8081;

		if (args != null && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				// 采用默认值
			}
		}

		new EchoClient("127.0.0.1", port).connect();
	}

	public void connect() {
		// 配置客户端NIO线程组
		EventLoopGroup group = new NioEventLoopGroup();
		// 客户端辅助启动类
		Bootstrap b = new Bootstrap();

		try {
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
					.handler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast("msgpackDecoder", new MsgpackDecoder());
							ch.pipeline().addLast("msgpackEncoder", new MsgpackEncoder());
							ch.pipeline().addLast(new EchoClientHandler());
						}
					});
			// 发起异步连接操作
			ChannelFuture future = b.connect(host, port).sync();

			System.out.println("Connection established!");

			// 等待客户端链路关闭
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 优雅退出，释放线程池资源
			group.shutdownGracefully();
		}
	}
}

class EchoClientHandler extends ChannelInboundHandlerAdapter {

	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		UserBean user = new UserBean("abcdefg", 111);
		ctx.writeAndFlush(user);

		List<UserBean> users = new ArrayList<UserBean>();
		for (int i = 0; i < 10; i++) {
			UserBean u = new UserBean("abcdefg" + i, 111 + i);
			users.add(u);
		}

		ctx.writeAndFlush(users);
	}

	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("Client receive the msgpack message : " + msg);
	}

	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
