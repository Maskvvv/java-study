package com.zhy.utils.file;

import org.apache.commons.lang3.ObjectUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
        System.out.println(handleRepeatFileName("D:\\UserFiles\\桌面", "域名.txt"));
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

        String[] list = baseFile.list();
        if (ObjectUtils.isEmpty(list)) return fileName;

        List<Integer> numList = new ArrayList<>();
        for (String name : list) {
            Matcher matcher = pattern.matcher(name);
            if (matcher.find()) {
                int order = Integer.parseInt(matcher.group(1));
                numList.add(order);
            }
        }

        int finalOrder = 1;
        if (!numList.isEmpty()) {
            finalOrder = firstMissingPositive(numList);
        }



        StringBuilder finalFileName = new StringBuilder(fileName);
        finalFileName.insert(pointIndex > 0 ? pointIndex : fileName.length(), " (" + (finalOrder) + ")");

        return finalFileName.toString();
    }

    private static int firstMissingPositive(List<Integer> numList) {
        int[] nums = new int[numList.size()];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = numList.get(i);
        }

        int length = nums.length;
        for (int i = 0; i < length; i++) {
            while (nums[i] >= 1 && nums[i] <= length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }

        return length + 1;
    }

   private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
   }


}
