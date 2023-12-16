package com.zhy.spring.aop.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author zhouhongyin
 * @since 2023/12/16 17:10
 */
@Service
public class TransactionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @MyTransaction
    @Transactional
    public String test1() throws SQLException {
        String sql = "SELECT * FROM `user`;";

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

        System.out.println(maps);
        return "1111";

    }
}
