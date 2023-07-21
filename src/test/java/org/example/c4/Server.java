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
        // 切换成非阻塞模式
        ssc.configureBlocking(false);

        // 2. 绑定监听端口
        ssc.bind(new InetSocketAddress(10001));

        // 3. 连接集合
        List<SocketChannel> channels = new ArrayList<>();
        while (true) {
            // 4. accept 建立与客户端的连接, SocketChannel 用来与客户端之间通信
            // 非阻塞方法, 线程还会继续运行, 如果没有连接建立, sc 为 null
            SocketChannel sc = ssc.accept();
            if (sc != null) {
                log.info("connected:{}", sc);
                // 非阻塞模式
                sc.configureBlocking(false);
                channels.add(sc);
            }
            for (SocketChannel channel : channels) {
                // 5. 接收客户端发送的数据
                // 非阻塞模式, 线程仍然会继续运行, 如果没有读到数据, read 返回 0
                int read = channel.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    debugRead(buffer);
                    buffer.clear();
                    log.info("after read:{}", channel);
                }
            }
        }

    }
}
