package org.example.c1;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static org.example.c1.ByteBufferUtil.debugAll;

/**
 * ByteBuffer和字符串的转换
 *
 * @className: StringTestByteBuffer
 * @author: 齐向前
 * @date: 2023/7/14
 **/
public class TestByteBufferString {

    public static void main(String[] args) {
        // 1. 字符串转为 ByteBuffer
        ByteBuffer buffer1 = ByteBuffer.allocate(16);
        buffer1.put("hello".getBytes());
        debugAll(buffer1);

        // 2. Charset
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello");
        debugAll(buffer2);

        // 3. wrap
        ByteBuffer buffer3 = ByteBuffer.wrap("hello".getBytes());
        debugAll(buffer3);

        // 4. 转为字符串
        String str1 = StandardCharsets.UTF_8.decode(buffer2).toString();
        System.out.println(str1);

        buffer1.flip();
        String str2 = StandardCharsets.UTF_8.decode(buffer1).toString();
        System.out.println(str2);
    }
}
