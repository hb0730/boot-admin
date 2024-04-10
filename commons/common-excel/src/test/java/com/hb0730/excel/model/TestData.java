package com.hb0730.excel.model;

import com.hb0730.excel.annotation.ExcelField;
import com.hb0730.excel.filedtype.MoneyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestData implements Serializable {
    @ExcelField(title = "姓名", order = 1)
    private String name;
    @ExcelField(title = "年龄", order = 2)
    private Integer age;
    @ExcelField(title = "日期", order = 3)
    private Date date;
    @ExcelField(title = "地址", order = 4)
    private String address;


    /**
     * 获取电话
     *
     * @return 电话
     */
    @ExcelField(title = "电话", order = 5)
    public String getPhone() {
        return "123456789";
    }

    @ExcelField(title = "金额", order = 6, fieldType = MoneyType.class)
    private BigDecimal money;
}
