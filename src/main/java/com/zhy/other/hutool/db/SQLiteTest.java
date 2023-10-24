package com.zhy.other.hutool.db;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.DSFactory;
import cn.hutool.setting.Setting;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * <p> SQLite test</p>
 *
 * @author zhouhongyin
 * @since 2022/7/5 11:14
 */
public class SQLiteTest {

    @Test
    public void dbTest1() throws SQLException {
        //自定义数据库Setting，更多实用请参阅Hutool-Setting章节
        Setting setting = new Setting("sqlite.setting");
        //获取指定配置，第二个参数为分组，用于多数据源，无分组情况下传null
        // 注意此处DSFactory需要复用或者关闭
        DataSource ds = DSFactory.create(setting).getDataSource();

        List<Company> company = Db.use(ds).findAll(Entity.create().setTableName("COMPANY"), Company.class);
        System.out.println(JSON.toJSONString(company));
    }

    @Data
    public static class Company {
        private Integer age;
        private Integer id;
        private String address;
        private Integer salary;
        private String name;
    }
}
