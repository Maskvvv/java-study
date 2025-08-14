package com.zhy.other.groovy.loader;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

/**
 * 获取加载类名称
 *
 * @author zhouhongyin
 * @since 2023/5/20 11:54
 */
//@Component
public class MyLoader1 implements ILoader {

    @Autowired
    private AutowiredBean autowiredBean;

    public String process() throws Exception {
        autowiredBean.print();
        DataSource bean = SpringUtil.getBean(DataSource.class);
        
        // 获取数据库连接信息
        Connection connection = null;
        try {
            connection = bean.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            
            // 构建连接信息对象
            java.util.Map<String, Object> connectionInfo = new java.util.HashMap<>();
            connectionInfo.put("url", metaData.getURL());
            connectionInfo.put("username", metaData.getUserName());
            connectionInfo.put("databaseProductName", metaData.getDatabaseProductName());
            connectionInfo.put("databaseProductVersion", metaData.getDatabaseProductVersion());
            connectionInfo.put("driverName", metaData.getDriverName());
            connectionInfo.put("driverVersion", metaData.getDriverVersion());
            connectionInfo.put("catalog", connection.getCatalog());
            connectionInfo.put("schema", connection.getSchema());
            connectionInfo.put("autoCommit", connection.getAutoCommit());
            connectionInfo.put("readOnly", connection.isReadOnly());
            connectionInfo.put("transactionIsolation", connection.getTransactionIsolation());
            
            return JSON.toJSONString(connectionInfo, SerializerFeature.PrettyFormat);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
