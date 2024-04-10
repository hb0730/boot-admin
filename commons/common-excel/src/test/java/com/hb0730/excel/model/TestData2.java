package com.hb0730.excel.model;

import com.hb0730.excel.annotation.ExcelField;
import com.hb0730.excel.annotation.ExcelFields;
import com.hb0730.excel.filedtype.MoneyType;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/10
 */
@ExcelFields({
        @ExcelField(title = "姓名", attrName = "name", order = 1),
        @ExcelField(title = "年龄", attrName = "age", order = 2),
        @ExcelField(title = "日期", attrName = "date", order = 3),
        @ExcelField(title = "地址", attrName = "address", order = 4),
        @ExcelField(title = "电话", attrName = "phone", order = 5),
        @ExcelField(title = "金额", attrName = "money", order = 6, fieldType = MoneyType.class)
})
public class TestData2 {
}
