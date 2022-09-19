package com.zhy.常用java类.trie;

import org.apache.commons.collections4.trie.PatriciaTrie;
import org.junit.Test;

import java.util.Map;
import java.util.SortedMap;

/**
 * @author zhouhongyin
 * @since 2022/9/18 14:20
 */
public class TrieTest {

    @Test
    public void trieTest() {
        PatriciaTrie<Integer> trie = new PatriciaTrie<>();

        trie.put("ab", 1);
        trie.put("abc", 2);
        trie.put("bd", 3);
        trie.put("aa", 4);

        System.out.println(trie.get("abc"));

        SortedMap<String, Integer> ab = trie.prefixMap("ab");
        for (Map.Entry<String, Integer> stringIntegerEntry : ab.entrySet()) {
            System.out.println(stringIntegerEntry);
        }

        System.out.println(trie.firstKey());

        System.out.println(trie.select("eeeebd"));

        String s = "aa";
        char[] chars = s.toCharArray();
    }

}
