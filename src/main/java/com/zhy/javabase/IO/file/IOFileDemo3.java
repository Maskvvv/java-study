package com.zhy.javabase.IO.file;


import org.apache.commons.lang3.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 */

public class IOFileDemo3 {
    public static void main(String[] args) throws IOException {
        File file = new File("G:\\qst\\qst需求文档\\迭代9\\dowlond");
        List<String> res = new ArrayList<>();
        res.add("dowlond");
        listDictionaries(res, "-", file);
        for (String re : res) {
            System.out.println(re);
        }


    }

    public static void listDictionaries(List<String> res, String prefix, File path) {
        if (!path.exists() || !path.isDirectory() || ObjectUtils.isEmpty(path.listFiles())) {
            return;
        }

        for (File currentFile : Objects.requireNonNull(path.listFiles())) {
            res.add(prefix + currentFile.getName());
            if (currentFile.isDirectory()) {
                listDictionaries(res, " " + prefix, currentFile);
            }
        }

    }
}
