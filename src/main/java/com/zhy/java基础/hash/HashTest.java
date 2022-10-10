package com.zhy.java基础.hash;

import cn.hutool.core.util.HashUtil;
import org.junit.Test;

/**
 * @author zhouhongyin
 * @since 2022/9/27 10:16
 */
public class HashTest {

    @Test
    public void hashTest() {

        String key = "ssfaskjasdfjgkfjal;ksdfgsdfgsdfgdflskgjasdfalkssdfasdg";

        System.out.println(key.hashCode());


        int h;
        int hash = (h = key.hashCode()) ^ (h >>> 16);
        System.out.println(hash);

        int length = 16;
        System.out.println(hash & (length - 1));
        System.out.println(hash % length);
    }

    @Test
    public void hashTest1() {

        String key = "glskadjflakjf52asdkjflasdjglshgjklasdfgsdfgsdfghsgalshdlfkasdhflaksdfhasldhgjhoiirutwi" +
                "oeurq093457298345-1231;lgsdfglks;l&()*^^%^&#^_:nasfhworeLKjlksdjf;ladgya9oiuwrwerwerwerkas";


        int h = 0;
        char[] value = key.toCharArray();

        for (int i = 0; i < value.length; i++) {
            h = 31 * h + value[i];
        }

        System.out.println(h);

        System.out.println(key.hashCode());
    }

    @Test
    public void hashTest2() {

        String key = "glskadjflakjf52asdkjflasdjglshgjklahsgalshdlfkasdhflaksdfhasldhgjhoiirutwi" +
                "oeurq093457298345-1231;lgsdfglks;l&()*^^%^&#^_:nasfhworeLKjlksdjf;ladgya9oiuwrwerwerwerkas";

        int hash = HashUtil.javaDefaultHash(key);
        System.out.println(hash);
        System.out.println(key.hashCode());
    }

    @Test
    public void hashTest3() {

        String key = null;
        System.out.println(key.hashCode());
    }

}
