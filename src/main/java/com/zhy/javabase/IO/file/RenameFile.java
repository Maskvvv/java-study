package com.zhy.javabase.IO.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * <p> rename file </p>
 *
 * @author zhouhongyin
 * @since 2023/10/23 22:00
 */
public class RenameFile {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\lenovo\\Desktop\\";
        String fileName = "sql.txt";
        String fileNameNew = "sql11111.txt";
        renameFile(filePath, fileName, fileNameNew);
    }

    public static void renameFile(String filePath, String fileName, String fileNameNew) {
        String oldFileName = filePath + "/" + fileName;
        File oldFile = new File(oldFileName);
        String newFileName = filePath + "/" + fileNameNew;
        File newFile = new File(newFileName);
        if (oldFile.exists() && oldFile.isFile()) {
            oldFile.renameTo(newFile);
        }
    }


    @Test
    public void test1() throws IOException {

        // 旧的文件或目录
        File oldName = new File("C:\\Users\\lenovo\\Desktop\\sql11111.txt");
        // 新的文件或目录
        File newName = new File("C:\\Users\\lenovo\\Desktop\\sql.txt");
        if (newName.exists()) {  //  确保新的文件名不存在
            throw new IOException("file exists");
        }
        if (oldName.renameTo(newName)) {
            System.out.println("已重命名");
        } else {
            System.out.println("Error");
        }
    }


}
