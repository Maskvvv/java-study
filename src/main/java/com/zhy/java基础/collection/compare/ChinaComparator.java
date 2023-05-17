package com.zhy.java基础.collection.compare;

import java.text.CollationKey;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * @author zhouhongyin
 * @since 2023/5/17 13:40
 */
public class ChinaComparator {
    public static void main(String[] args) {
        List<String> data = new ArrayList<String>();
        data.add("上海");
        data.add("天津");
        data.add("北京");
        data.add("深圳");
        data.add("广州");
        data.add("成都");
        data.add("西安");
        data.add("武汉");
        data.add("郑州");
        data.add("邯郸");

        Collections.sort(data, new Comparator<String>() {
            Collator collator = Collator.getInstance(Locale.CHINA);
            @Override
            public int compare(String o1, String o2) {
                CollationKey key1 = collator.getCollationKey(o1);
                CollationKey key2 = collator.getCollationKey(o2);
                return key1.compareTo(key2);
            }
        });

        for (String str : data) {

            System.out.print(str + "--");

        }
    }


}
