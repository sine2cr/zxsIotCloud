package cn.sine2cr.zxsiotcloudgateway.handler.http;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author Sine2cr
 * @Date 2024/3/10
 * @Mail sine2cr@163.com
 **/
public class MessageSender {
    public static void success(ChannelHandlerContext ctx){
        ctx.channel().writeAndFlush(
                HttpResponse.successResponse(
                         " success"));
    }
    public static void success(ChannelHandlerContext ctx, String content){
        ctx.channel().writeAndFlush(
                HttpResponse.successResponse(
                        content));
    }
    public static void fail(ChannelHandlerContext ctx){
        ctx.channel().writeAndFlush(
                HttpResponse.failResponse(
                        " fail"));
    }
    public static void fail(ChannelHandlerContext ctx, String content){
        ctx.channel().writeAndFlush(
                HttpResponse.failResponse(
                        content));
    }
}
