package com.zhy.常用java类.byt;

import org.junit.jupiter.api.Test;

/**
 * <p> byte test </p>
 *
 * @author zhouhongyin
 * @since 2023/8/4 9:47
 */
public class ByteTest {
    @Test
    public void array() {
        //二进制
        byte b = 0b1001;
        System.out.println(b);

        //十六进制
        byte x = 0x09;
        System.out.println(x);

        byte[] ba = {0b1001, 0b1001};
        System.out.println(ba);

        byte s = Byte.parseByte("9");
        System.out.println(s);
    }
    @Test
    public void byt() {
        //二进制
        byte b = 0b1001;
        System.out.println(b);

        //十六进制
        byte x = 0x09;
        System.out.println(x);

        byte[] ba = {0b1001, 0b1001};
        System.out.println(ba);

        byte s = Byte.parseByte("9");
        System.out.println(s);
    }

    @Test
    public void byteToString() {
        //二进制
        String binaryString = Integer.toBinaryString(9);
        System.out.println(binaryString);
        //八进制
        String octalString = Integer.toOctalString(9);
        System.out.println(octalString);
        //十六进制
        String hexString = Integer.toHexString(9);
        System.out.println(hexString);
    }

    @Test
    public void stringToByte() {
        //二进制
        Integer.valueOf("0101",2).toString();
        //八进制
        Integer.valueOf("376",8).toString();
        //十六进制
        Integer.valueOf("FFFF",16).toString();

        byte b = 0b1001;
    }


}
