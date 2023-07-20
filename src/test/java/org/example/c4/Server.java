package org.example.c4;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static org.example.c1.ByteBufferUtil.debugRead;

/**
 * @author qxq
 * @date 2023/7/20
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        // 使用nio来理解阻塞模式, 单线程
        // 0. ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);

        // 1. 创建了服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();

        // 2. 绑定监听端口
        ssc.bind(new InetSocketAddress(10000));

        // 3. 连接集合
        List<SocketChannel> channels = new ArrayList<>();
        while (true) {
            // 4. accept 建立与客户端的连接, SocketChannel 用来与客户端之间通信
            log.info("connecting...");
            // 阻塞方法, 线程停止运行
            SocketChannel sc = ssc.accept();
            log.info("connected:{}", sc);
            channels.add(sc);
            for (SocketChannel channel : channels) {
                // 5. 接收客户端发送的数据
                log.info("before read:{}", channel);
                // 阻塞方法, 线程停止运行
                channel.read(buffer);
                buffer.flip();
                debugRead(buffer);
                buffer.clear();
                log.info("after read:{}", channel);
            }
        }

    }
}
