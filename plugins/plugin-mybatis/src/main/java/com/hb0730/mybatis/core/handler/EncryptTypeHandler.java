package com.hb0730.mybatis.core.handler;

import com.hb0730.base.utils.StrUtil;
import com.hb0730.mybatis.core.encrypt.MybatisEncryptService;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 加密类型处理器
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
//@MappedJdbcTypes({JdbcType.VARCHAR})
//@MappedTypes({String.class})
public class EncryptTypeHandler extends BaseTypeHandler<String> {
    private final MybatisEncryptService mybatisEncryptService;

    public EncryptTypeHandler(MybatisEncryptService mybatisEncryptService) {
        this.mybatisEncryptService = mybatisEncryptService;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, mybatisEncryptService.encrypt(parameter));
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String columnValue = rs.getString(columnName);
        //有一些可能是空字符
        return StrUtil.isBlank(columnValue) ? columnValue : mybatisEncryptService.decrypt(columnValue);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String columnValue = rs.getString(columnIndex);
        //有一些可能是空字符
        return StrUtil.isBlank(columnValue) ? columnValue : mybatisEncryptService.decrypt(columnValue);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String columnValue = cs.getString(columnIndex);
        //有一些可能是空字符
        return StrUtil.isBlank(columnValue) ? columnValue : mybatisEncryptService.decrypt(columnValue);
    }
}
