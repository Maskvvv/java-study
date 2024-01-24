package com.zhy.utils.apifox;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2024/1/24 15:07
 */
public class JsonSchemaUtils {


    @Test
    public void test() {


        ExcelReader libReader = ExcelUtil.getReader("D:\\UserFiles\\桌面\\新建 Microsoft Excel 工作表.xlsx", 0);
        List<List<Object>> list = libReader.read();

        JsonSchema jsonSchema = new JsonSchema("object", "基本信息");
        for (List<Object> row : list) {
            JsonSchemaProperties jsonSchemaProperties = new JsonSchemaProperties();
            jsonSchemaProperties.setType((String) row.get(3));
            jsonSchemaProperties.setTitle((String) row.get(1));
            jsonSchemaProperties.setDescription((String) row.get(2));

            jsonSchema.putProperties((String) row.get(0), jsonSchemaProperties);
        }


        System.out.println(JSON.toJSONString(jsonSchema));

    }



}
