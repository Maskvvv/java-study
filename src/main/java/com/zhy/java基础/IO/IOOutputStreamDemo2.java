package com.zhy.java基础.IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class IOOutputStreamDemo2 {
    public static void main(String[] args) throws Exception {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            outputStream = new FileOutputStream("b.txt");
            inputStream = new FileInputStream("a.txt");

            outputStream.write(inputStream.read());


        }finally {

            if (outputStream != null){
                outputStream.close();
            }
            if (inputStream != null){
                inputStream.close();
            }
        }



    }
}
