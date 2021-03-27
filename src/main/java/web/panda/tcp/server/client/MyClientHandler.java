package web.panda.tcp.server.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush("hello,panda server！");
        ctx.channel().writeAndFlush("hello,panda server！");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("服务端" + channelHandlerContext.channel().remoteAddress() + "-----" + s);
        System.out.println("client receive" + s);
    }
}
