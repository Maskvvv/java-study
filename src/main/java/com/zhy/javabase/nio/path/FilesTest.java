package com.zhy.javabase.nio.path;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/7/21 9:47
 */
public class FilesTest {

    /**
     * 检查文件是否存在
     */
    @Test
    public void test1() {
        Path path = Paths.get("helloword/data.txt");
        System.out.println(Files.exists(path));
    }

    /**
     * 创建一级目录
     */
    @Test
    public void test2() throws IOException {
        Path path = Paths.get("helloword/d1");
        Files.createDirectory(path);
    }

    /**
     * 创建多级目录用
     */
    @Test
    public void test3() throws IOException {
        Path path = Paths.get("helloword/d1/d2");
        Files.createDirectories(path);
    }

    /**
     * 拷贝文件
     */
    @Test
    public void test4() throws IOException {
        Path source = Paths.get("helloword/data.txt");
        Path target = Paths.get("helloword/target.txt");

        Files.copy(source, target);
    }

    /**
     * 拷贝文件 存在则覆盖
     */
    @Test
    public void test5() throws IOException {
        Path source = Paths.get("helloword/data.txt");
        Path target = Paths.get("helloword/target.txt");

        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * 移动文件
     */
    @Test
    public void test6() throws IOException {
        Path source = Paths.get("helloword/data.txt");
        Path target = Paths.get("helloword/data.txt");
        //StandardCopyOption.ATOMIC_MOVE 保证文件移动的原子性
        Files.move(source, target, StandardCopyOption.ATOMIC_MOVE);
    }

    /**
     * 删除文件
     * - 如果文件不存在，会抛异常 NoSuchFileException
     * - 如果目录还有内容，会抛异常 DirectoryNotEmptyException
     */
    @Test
    public void test7() throws IOException {
        Path target = Paths.get("helloword/target.txt");

        Files.delete(target);

    }



}
