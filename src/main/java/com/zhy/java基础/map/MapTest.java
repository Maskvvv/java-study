package com.zhy.java基础.map;

import com.zhy.java基础.map.entity.MapEntity;
import org.junit.Test;

/**
 *  
 * @author zhouhongyin
 * @since 2021/8/26 16:25
 */
public class MapTest {

    @Test
    public void mapTest1() {
        MapEntity mapEntity = new MapEntity();
        mapEntity.put("test","test");
        System.out.println(mapEntity.get("test"));
    }

}
