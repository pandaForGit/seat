package web.panda.tcp.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NettyServer {

    public void start() {
        System.out.println("netty Tcp 服务器启动");

        // 负责连接请求
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 负责事件响应
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 服务器启动项
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //handler是针对bossGroup，childHandler是针对workerHandler
            serverBootstrap.group(bossGroup, workerGroup)
                    // 选择nioChannel
                    .channel(NioServerSocketChannel.class)
                    // 日志处理 info级别
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 添加自定义的初始化器
                    .childHandler(new MyServerInitializer());
            // 端口绑定
            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            //该方法进行阻塞,等待服务端链路关闭之后继续执行。
            //这种模式一般都是使用Netty模块主动向服务端发送请求，然后最后结束才使用
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}

