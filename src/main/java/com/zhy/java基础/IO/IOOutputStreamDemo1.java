package com.zhy.java基础.IO;

import java.io.FileOutputStream;

import java.io.OutputStream;

public class IOOutputStreamDemo1 {
    public static void main(String[] args) throws Exception {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("a.txt");
            outputStream.write("hello word".getBytes("utf-8"));


        }finally {

            if (outputStream != null){
                outputStream.close();
            }
        }



    }
}
