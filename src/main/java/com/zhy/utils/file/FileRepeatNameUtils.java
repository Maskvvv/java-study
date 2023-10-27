package com.zhy.utils.file;

import cn.hutool.core.text.StrBuilder;
import org.apache.commons.lang3.ObjectUtils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> Handle Repeat File Name Utils </p>
 *
 * @author zhouhongyin
 * @since 2023/10/27 15:56
 */
public class FileRepeatNameUtils {

    public static void main(String[] args) {
        System.out.println(handleRepeatFileName("D:\\UserFiles\\桌面", "报名信息_20231026.xlsx"));
    }


    /**
     * <p> 处理重复文件名 </p>
     *
     * 例子：
     * <pre>
     * 1. 有后缀文件
     * a.txt -> a (1).txt
     *
     * 2. 没有后缀文件
     * aaa -> aaa (1)
     *
     * 3. 该路径不存在该重复文件
     * aaa -> aaa
     * </pre>
     *
     * @param basePath 待处理的文件路径 {@code D:\UserFiles\桌面}
     * @param fileName 文件名 {@code a.txt}
     * @return 如果存在重复文件则返回 {@code a (1).txt}
     */
    public static String handleRepeatFileName(String basePath, String fileName) {
        String fullFileName = basePath + File.separator + fileName;

        File file = new File(fullFileName);
        if (!file.exists()) {
            return fileName;
        }

        int pointIndex = fileName.lastIndexOf(".");
        String finalPrefixFileName = fileName;
        if (pointIndex > 0) {
            finalPrefixFileName = fileName.substring(0, pointIndex);
        }

        String format = String.format("(?<=%s) \\((\\d+)\\).*", finalPrefixFileName);
        Pattern pattern = Pattern.compile(format);

        File baseFile = new File(basePath);

        int maxOrder = 0;
        String[] list = baseFile.list();
        if (ObjectUtils.isEmpty(list)) return fileName;
        for (String name : list) {
            Matcher matcher = pattern.matcher(name);
            if (matcher.find()) {
                int order = Integer.parseInt(matcher.group(1));
                maxOrder = Math.max(maxOrder, order);
            }
        }

        StrBuilder finalFileName = new StrBuilder(fileName);
        finalFileName.insert(pointIndex > 0 ? pointIndex : fileName.length(), " (" + (maxOrder + 1) + ")");

        return finalFileName.toString();
    }

}
