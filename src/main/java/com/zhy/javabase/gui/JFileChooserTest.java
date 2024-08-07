package com.zhy.javabase.gui;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

/**
 * <p> JFileChooser </p>
 *
 * @author zhouhongyin
 * @since 2023/10/23 16:18
 */
public class JFileChooserTest {

    public static void main(String[] args) {
        //使用系统的文件管理器
        if (UIManager.getLookAndFeel().isSupportedLookAndFeel()) {
            final String platform = UIManager.getSystemLookAndFeelClassName();
            // If the current Look & Feel does not match the platform Look & Feel,
            // change it so it does.
            if (!UIManager.getLookAndFeel().getName().equals(platform)) {
                try {
                    UIManager.setLookAndFeel(platform);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }


        String userHome = System.getProperty("user.dir");

        FileSystemView fsv = FileSystemView.getFileSystemView();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
        fileChooser.setDialogTitle("请选择要上传的文件...");
        fileChooser.setApproveButtonText("确定");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setCurrentDirectory(new File(userHome));


        int result = fileChooser.showOpenDialog(null);

        if (JFileChooser.APPROVE_OPTION == result) {
            String path = fileChooser.getSelectedFile().getPath();
            System.out.println("path: " + path);
        }

    }
}
