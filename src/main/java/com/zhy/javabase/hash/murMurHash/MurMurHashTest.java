package com.zhy.javabase.hash.murMurHash;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouhongyin
 * @since 2022/10/11 14:50
 */
public class MurMurHashTest {

    @Test
    public void MurMurHashUtilsTest() {
        List<String> list = new ArrayList<>();
        list.add("CHONGQING");
        list.add("CHANGSHA");
        list.add("GUANGZHOU");
        list.add("SHENZHEN");
        list.add("001c4becd89f49f7b1c52fe4fcd54397");
        list.add("002b320c0e0347a8bcea7663192d8303");
        list.add("0035420515a24d9d875e6c4399bec8e3");
        list.add("00701f4c12364bedb626dd136cbc998b");
        list.add("008d028903da483fbee8d721b2e73934");
        list.add("00b9dce4ec2747c0aea6eb0494e38717");
        for (String city : list) {
            long hash = MurMurHashUtil.hashUnsigned(city);
            System.out.println(hash);
            System.out.println(hash % 10);
        }
    }

    @Test
    public void GuavaMurMurHashUtilsTest() {
        List<String> list = new ArrayList<>();
        list.add("CHONGQING");
        list.add("CHANGSHA");
        list.add("GUANGZHOU");
        list.add("SHENZHEN");
        list.add("001c4becd89f49f7b1c52fe4fcd54397");
        list.add("002b320c0e0347a8bcea7663192d8303");
        list.add("0035420515a24d9d875e6c4399bec8e3");
        list.add("00701f4c12364bedb626dd136cbc998b");
        list.add("008d028903da483fbee8d721b2e73934");
        list.add("00b9dce4ec2747c0aea6eb0494e38717");
        for (String city : list) {
            long hash = GuavaMurMurHashUtils.hashUnsigned(city);
            System.out.println(hash);
            System.out.println(hash % 10);
        }
    }

    @Test
    public void sha256() {
        List<String> list = new ArrayList<>();
        list.add("CHONGQING");
        list.add("CHANGSHA");
        list.add("GUANGZHOU");
        list.add("SHENZHEN");
        list.add("001c4becd89f49f7b1c52fe4fcd54397");
        list.add("002b320c0e0347a8bcea7663192d8303");
        list.add("0035420515a24d9d875e6c4399bec8e3");
        list.add("00701f4c12364bedb626dd136cbc998b");
        list.add("008d028903da483fbee8d721b2e73934");
        list.add("00b9dce4ec2747c0aea6eb0494e38717");
        for (String city : list) {

            HashFunction hashFunction = Hashing.sha256();
            long hash = hashFunction.hashString(city, StandardCharsets.UTF_8).asLong();
            System.out.println(hashFunction.hashString(city, StandardCharsets.UTF_8));
            System.out.println(hash);
            System.out.println(hash % 10);
        }
    }

    @Test
    public void md5() {
        List<String> list = new ArrayList<>();
        list.add("CHONGQING");
        list.add("CHANGSHA");
        list.add("GUANGZHOU");
        list.add("SHENZHEN");
        list.add("001c4becd89f49f7b1c52fe4fcd54397");
        list.add("002b320c0e0347a8bcea7663192d8303");
        list.add("0035420515a24d9d875e6c4399bec8e3");
        list.add("00701f4c12364bedb626dd136cbc998b");
        list.add("008d028903da483fbee8d721b2e73934");
        list.add("00b9dce4ec2747c0aea6eb0494e38717");
        for (String city : list) {

            HashFunction hashFunction = Hashing.hmacMd5("s".getBytes());
            long hash = hashFunction.hashString(city, StandardCharsets.UTF_8).asLong();
            System.out.println(hashFunction.hashString(city, StandardCharsets.UTF_8));
            System.out.println(hash);
            System.out.println(hash % 10);
        }
    }




}
