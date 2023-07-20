package org.example.c1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @className: TestFilesCopy
 * @author: 齐向前
 * @date: 2023/7/17
 **/
public class TestFilesCopy {

    public static void main(String[] args) throws IOException {
        String source = "C:\\Users\\Administrator\\Desktop\\message-queue-rabbit_product_diding";
        String target = "C:\\Users\\Administrator\\Desktop\\message-queue-rabbit_product_diding----";

        Files.walk(Paths.get(source)).forEach(path -> {
            try {
                String targetName = path.toString().replace(source, target);
                // 是目录
                Path dir = Paths.get(targetName);
                if (Files.isDirectory(path)) {
                    Files.createDirectory(dir);
                }
                // 是普通文件
                else if (Files.isRegularFile(path)) {
                    Files.copy(path, dir);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
