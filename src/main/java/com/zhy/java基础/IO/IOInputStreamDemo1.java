package com.zhy.java基础.IO;

import java.io.File;
import java.io.FileInputStream;

import java.io.InputStream;

public class IOInputStreamDemo1 {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = null;
        try {
            File file = new File("./a.txt");

            inputStream = new FileInputStream(file);

            int n;

            while ((n = inputStream.read()) != -1) { // 利用while同时读取并判断
                System.out.print((char) (n));
                System.out.printf(" %d \n",n);
            }


        }finally {

            if (inputStream != null){
                inputStream.close();
            }
        }



    }
}
