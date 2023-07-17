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
//        Files.delete(Paths.get("C:\\Users\\Administrator\\Desktop\\图片 - 副本"));
        Files.walkFileTree(Paths.get("C:\\Users\\Administrator\\Desktop\\message-queue-rabbit_product_diding - 副本"),new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                System.out.println(file);
                Files.delete(file);
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
//                System.out.println("====> 退出" + dir);
                Files.delete(dir);
                return super.postVisitDirectory(dir, exc);
            }
        });
    }

    private static void m2() throws IOException {
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
