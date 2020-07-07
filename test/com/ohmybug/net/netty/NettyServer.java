package com.ohmybug.net.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.EventExecutorGroup;

public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        // 负责接客
        EventLoopGroup boosGroup = new NioEventLoopGroup(2);
        // 负责服务
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);
        // Server启动辅助类
        ServerBootstrap b = new ServerBootstrap();

        b.group(boosGroup, workerGroup);
        // 异步全双工
        b.channel(NioServerSocketChannel.class);
        // netty 帮我们内部处理了accept的过程
        b.childHandler(new MyChildInitializer());
        b.bind(8888).sync();
    }
}

class MyChildInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new MyChildHandler());
    }

}

class MyChildHandler extends ChannelInboundHandlerAdapter {

}
