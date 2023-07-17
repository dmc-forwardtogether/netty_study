package org.example.c1;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className: TestFilesWalkFileTree
 * @author: 齐向前
 * @date: 2023/7/17
 **/
public class TestFilesWalkFileTree {

    public static void main(String[] args) throws IOException {
        AtomicInteger vueCount = new AtomicInteger();
        Files.walkFileTree(Paths.get("C:\\Users\\Administrator\\Desktop\\vue_test\\vue_demo6"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".vue")) {
                    System.out.println(file);
                    vueCount.incrementAndGet();
                }
                return super.visitFile(file, attrs);
            }
        });
        System.out.println("vue count:" + vueCount);
    }

    private static void m1() throws IOException {
        AtomicInteger dirCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();

        Files.walkFileTree(Paths.get("C:\\Users\\Administrator\\Desktop\\vue_test\\vue_demo6"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("====>" + dir);
                dirCount.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                fileCount.incrementAndGet();
                return super.visitFile(file, attrs);
            }
        });
        System.out.println("dir count" + dirCount);
        System.out.println("file count" + fileCount);
    }
}
