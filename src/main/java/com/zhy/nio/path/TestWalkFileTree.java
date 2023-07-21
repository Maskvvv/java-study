package com.zhy.nio.path;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p> walkFileTree </p>
 * 可以使用Files工具类中的walkFileTree(Path, FileVisitor)方法，其中需要传入两个参数
 *
 * - Path：文件起始路径
 * - FileVisitor：文件访问器，使用访问者模式
 *   - 接口的实现类SimpleFileVisitor有四个方法
 *    - preVisitDirectory：访问目录前的操作
 *    - visitFile：访问文件的操作
 *    - visitFileFailed：访问文件失败时的操作
 *    - postVisitDirectory：访问目录后的操作
 *
 *
 * @author zhouhongyin
 * @since 2023/7/21 10:00
 */
public class TestWalkFileTree {

    @Test
    public void test1() throws IOException {
        Path path = Paths.get("E:\\qst\\java-study\\src\\main\\java\\com\\zhy");
        // 文件目录数目
        AtomicInteger dirCount = new AtomicInteger();
        // 文件数目
        AtomicInteger fileCount = new AtomicInteger();

        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("===>" + dir);
                // 增加文件目录数
                dirCount.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                // 增加文件数
                fileCount.incrementAndGet();
                return super.visitFile(file, attrs);
            }
        });
        // 打印数目
        System.out.println("文件目录数:" + dirCount.get());
        System.out.println("文件数:" + fileCount.get());
    }

    /**
     * 删除多级目录
     */
    @Test
    public void test2() throws IOException {
        Path path = Paths.get("E:\\qst11\\java-study\\src\\main\\java\\com\\zhy");

        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            /**
             * 删文件
             */
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return super.visitFile(file, attrs);
            }

            /**
             * 删文件夹
             */
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

                Files.delete(dir);
                return super.postVisitDirectory(dir, exc);
            }

        });

    }


}
