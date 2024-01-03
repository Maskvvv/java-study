package com.zhy.spring.aop.controller;

import com.alibaba.fastjson.JSON;
import com.zhy.spring.aop.lock.AthenaLock;
import com.zhy.spring.aop.transaction.MyTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author zhouhongyin
 * @since 2023/12/16 17:10
 */
@Service
public class TransactionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Random random = new Random(1000);

    @MyTransaction
    @Transactional
    public String test1() throws SQLException {
        String sql = "SELECT * FROM `user`;";

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

        System.out.println(maps);
        return "1111";

    }

    @AthenaLock(spEl = "${ #key }")
    @Transactional
    public String lock(String key, String queryParam) throws SQLException {
        String sql = "SELECT * FROM `user`;";

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        System.out.println(maps);

        String id = String.valueOf(random.nextInt());
        String insert = String.format("INSERT INTO `test`.`user` (`id`, `name`, `age`) VALUES (%s, '%s', %s);", id, id, id) ;
        jdbcTemplate.execute(insert);

        return JSON.toJSONString(maps);

    }


    public static void main(String[] args) {
        String id = String.valueOf(random.nextInt());

        String insert =String.format("INSERT INTO `test`.`user` (`id`, `name`, `age`) VALUES (%s, '%s', %s);", id, id, id) ;

        System.out.println(insert);

    }
}
