package web.panda.tcp.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 本方法用作处理异常
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause.getClass() == io.netty.handler.timeout.ReadTimeoutException.class) {
            log.info("连接超时断开");
        } else {
            cause.printStackTrace();
            log.info("连接异常断开");
            ctx.close();
        }
    }

    /**
     * 信息获取完毕后操作
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("信息获取完毕");
        ctx.channel().writeAndFlush("信息获取完毕");
        ctx.flush();
    }


    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info(msg);
        ctx.channel().writeAndFlush("hello this is panda i got your message "+ msg);
    }
}

