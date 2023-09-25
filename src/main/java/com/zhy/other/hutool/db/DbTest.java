package com.zhy.other.hutool.db;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouhongyin
 * @since 2022/7/5 11:14
 */
public class DbTest {



    @Test
    public void dbTest1() throws SQLException {
        List<Entity> company = Db.use().findAll("company");
        //System.out.println(company);

        System.out.println(company.get(1).get("name"));
    }

    @Test
    public void byteTest() throws SQLException {

        List<Entity> company = Db.use().findAll("sort");
        for (Entity entity : company) {
            byte[] bytes = entity.getBytes("b");
            for (byte aByte : bytes) {
                System.out.print(Integer.toBinaryString(aByte));
            }

            System.out.println();
        }
    }

    @Test
    public void city() throws SQLException {

        List<String> res = new ArrayList<>();
        List<Entity> city = Db.use().findAll("dict_division");
        for (Entity entity : city) {
            String level = entity.getStr("level");
            if (!level.equals("2")) continue;

            String name = entity.getStr("name");
            if (name.endsWith("自治区") || name.endsWith("自治州") || name.endsWith("自治县") || name.endsWith("自治旗")) {
                continue;
                //name = name.substring(0, name.length() - 3);
            }
            if (name.endsWith("地区") || name.endsWith("半岛")) {
                name = name.substring(0, name.length() - 2);
            }

            char lastChar = name.charAt(name.length() - 1);
            if (lastChar == '区' || lastChar == '县' || lastChar == '市' || lastChar == '旗' || lastChar == '乡' || lastChar == '岛') {
                name = name.substring(0, name.length() - 1);
            }
            res.add(name);
            //System.out.println(name);
        }

        System.out.println(String.join(",", res));

    }

}
