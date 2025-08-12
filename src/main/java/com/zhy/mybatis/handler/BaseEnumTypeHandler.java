package com.zhy.mybatis.handler;

import com.zhy.mybatis.enums.BaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

/**
 * 通用枚举类型处理器
 * 支持所有实现BaseEnum接口的枚举类型与数据库值之间的自动转换
 * 
 * @param <E> 枚举类型，必须实现BaseEnum接口
 * @author zhy
 * @since 2025-08-11
 */
public class BaseEnumTypeHandler<E extends Enum<E> & BaseEnum<T>, T> extends BaseTypeHandler<E> {

    private final Class<E> enumClass;
    private final E[] enumConstants;

    /**
     * 构造函数
     * 
     * @param enumClass 枚举类型
     */
    public BaseEnumTypeHandler(Class<E> enumClass) {
        if (enumClass == null) {
            throw new IllegalArgumentException("枚举类型不能为空");
        }
        this.enumClass = enumClass;
        this.enumConstants = enumClass.getEnumConstants();
        if (enumConstants == null || enumConstants.length == 0) {
            throw new IllegalArgumentException("枚举类型必须包含枚举常量");
        }
    }

    /**
     * 设置参数到PreparedStatement
     * 将枚举转换为数据库存储的值
     * 
     * @param ps PreparedStatement对象
     * @param i 参数索引
     * @param parameter 枚举参数
     * @param jdbcType JDBC类型
     * @throws SQLException SQL异常
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        T code = parameter.getCode();
        
        if (code instanceof Integer) {
            ps.setInt(i, (Integer) code);
        } else if (code instanceof String) {
            ps.setString(i, (String) code);
        } else if (code instanceof Long) {
            ps.setLong(i, (Long) code);
        } else if (code instanceof Boolean) {
            ps.setBoolean(i, (Boolean) code);
        } else if (code instanceof Byte) {
            ps.setByte(i, (Byte) code);
        } else if (code instanceof Short) {
            ps.setShort(i, (Short) code);
        } else if (code instanceof Float) {
            ps.setFloat(i, (Float) code);
        } else if (code instanceof Double) {
            ps.setDouble(i, (Double) code);
        } else {
            // 对于不支持的类型，尝试使用 setObject 方法
            ps.setObject(i, code);
        }
    }

    /**
     * 从ResultSet获取结果并转换为枚举
     * 
     * @param rs ResultSet对象
     * @param columnName 列名
     * @return 枚举实例
     * @throws SQLException SQL异常
     */
    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        T code = (T) rs.getObject(columnName);
        return rs.wasNull() ? null : getEnumByCode(code);
    }

    /**
     * 从ResultSet获取结果并转换为枚举（通过列索引）
     * 
     * @param rs ResultSet对象
     * @param columnIndex 列索引
     * @return 枚举实例
     * @throws SQLException SQL异常
     */
    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        T code = (T) rs.getObject(columnIndex);
        return rs.wasNull() ? null : getEnumByCode(code);
    }

    /**
     * 从CallableStatement获取结果并转换为枚举
     * 
     * @param cs CallableStatement对象
     * @param columnIndex 列索引
     * @return 枚举实例
     * @throws SQLException SQL异常
     */
    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        T code = (T) cs.getObject(columnIndex);
        return cs.wasNull() ? null : getEnumByCode(code);
    }

    /**
     * 根据代码值获取对应的枚举实例
     * 
     * @param code 代码值
     * @return 枚举实例
     * @throws IllegalArgumentException 当找不到对应枚举时抛出
     */
    private E getEnumByCode(T code) {
        if (code == null) {
            return null;
        }

        Optional<E> enumOptional = Arrays.stream(enumConstants)
                .filter(e -> e.getCode().equals(code))
                .findFirst();

        return enumOptional.orElseThrow(() -> 
                new IllegalArgumentException(
                        String.format("未找到代码值为 %d 的 %s 枚举实例", code, enumClass.getSimpleName())
                )
        );
    }
}