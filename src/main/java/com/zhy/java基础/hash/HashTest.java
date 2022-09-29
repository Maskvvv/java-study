package com.zhy.java基础.hash;

import org.junit.Test;

/**
 * @author zhouhongyin
 * @since 2022/9/27 10:16
 */
public class HashTest {

    @Test
    public void hashTest() {

        String key = "ssfaskjasdfjgkfjal;ksdflskgjasdfalkssdfasdg";

        System.out.println(key.hashCode());


        int h;
        int hash = (h = key.hashCode()) ^ (h >>> 16);
        System.out.println(hash);

        int length = 16;
        System.out.println(hash & (length - 1));
        System.out.println(Math.abs(hash) % length);
    }

    @Test
    public void hashTest1() {

        String key = "glskadjflakjf52asdkjflasdjglshgjklahsgalshdlfkasdhflaksdfhasldhgjhoiirutwi" +
                "oeurq093457298345-1231;lgsdfglks;l&()*^^%^&#^_:nasfhworeLKjlksdjf;ladgya9oiuwrwerwerwerkas";

        char[] chars = key.toCharArray();

        int result = 1;
        for (char cha : chars) {
            result = 31 * result + cha;
        }
        System.out.println(result);

        System.out.println(key.hashCode());
    }

}
