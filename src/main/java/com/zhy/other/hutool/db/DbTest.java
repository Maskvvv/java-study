package com.zhy.other.hutool.db;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import org.junit.Test;

import java.sql.SQLException;
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

}
