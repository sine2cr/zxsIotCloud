package cn.sine2cr.zxsiotcloudgateway;


import cn.sine2cr.zxsiotcloudcommon.common.ErrorCode;
import cn.sine2cr.zxsiotcloudcommon.exception.BusinessException;
import cn.sine2cr.zxsiotcloudgateway.constant.ProtocolConstant;
import cn.sine2cr.zxsiotcloudgateway.protocol.HttpChannelPipeline;
import cn.sine2cr.zxsiotcloudgateway.protocol.WebsocketChannelPipeline;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.ahocorasick.trie.Token;
import org.ahocorasick.trie.Trie;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Sine2cr
 * @Date 2024/2/18
 * @Mail sine2cr@163.com
 * @Version 1.0
 **/
public class ProtocolIdentifierAdapter extends ByteToMessageDecoder {

    private static final List<String> keys = Arrays.asList("HTTP","Upgrade: websocket");

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        String protocol = protocolParse(byteBuf);
        initChannel(protocol, channelHandlerContext);
        ByteBuf out = byteBuf.retainedDuplicate();
        list.add(out);
        byteBuf.skipBytes(byteBuf.readableBytes());
        channelHandlerContext.pipeline().remove(ProtocolIdentifierAdapter.class);
    }

    /**
     * 协议解析器
     * 基于AC自动机算法分析tcp包实现区分协议
     *
     * @return ProtocolConstant
     */
    private String protocolParse(ByteBuf byteBuf) {
        String tcpPacket = byteBuf.toString(StandardCharsets.UTF_8);

        Trie trie = Trie.builder()
                .ignoreOverlaps()
                .ignoreCase()
                .addKeywords(keys)
                .build();
        Collection<Token> tokens = trie.tokenize(tcpPacket);
        for (Token token : tokens) {
            if (token.isMatch()){
                boolean b = keys.get(1).equals(token.getFragment());
                if (b) {
                    return ProtocolConstant.WEBSOCKET;
                }
            }
        }
        return ProtocolConstant.HTTP;
    }


    /**
     * 根据相应协议初始化通道
     * @param protocol
     * @param channelHandlerContext
     */
    private void initChannel(String protocol, ChannelHandlerContext channelHandlerContext) {
        switch (protocol) {
            case ProtocolConstant.HTTP:
                HttpChannelPipeline.initChannel(channelHandlerContext.pipeline());
                break;
            case ProtocolConstant.WEBSOCKET:
                WebsocketChannelPipeline.initChannel(channelHandlerContext.pipeline());
                break;
            default:
                throw new BusinessException(ErrorCode.PROTOCOL_ERROR);
        }
    }
}



