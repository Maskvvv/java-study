package com.zhy.java基础.IO;

import java.io.FileInputStream;
import java.io.InputStream;

public class IOInputStreamDemo2 {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("./a.txt");

            byte[] bytes = new byte[1024];

            int n;
            while ((n = inputStream.read(bytes)) != -1) { // 利用while同时读取并判断
                System.out.println( n);
            }




        }finally {

            if (inputStream != null){
                inputStream.close();
            }
        }



    }
}
