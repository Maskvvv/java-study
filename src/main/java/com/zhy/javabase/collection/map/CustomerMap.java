package com.zhy.javabase.collection.map;

import com.zhy.javabase.collection.map.entity.MapEntity;
import org.junit.Test;

/**
 * @author zhouhongyin
 * @since 2023/2/16 21:45
 */
public class CustomerMap {

    @Test
    public void mapTest1() {
        MapEntity mapEntity = new MapEntity();
        mapEntity.put("test","test");
        System.out.println(mapEntity.get("test"));
    }

}
