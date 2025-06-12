package com.hb0730.poi.filedtype;


import com.hb0730.base.utils.StrUtil;
import com.hb0730.poi.annotation.ExcelField;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/9
 */
public class MoneyType implements FieldType {
    private NumberFormat nf = new DecimalFormat(",##0.00");

    @Override
    public Object getValue(String val, ExcelField excelField) {
        return StrUtil.isEmpty(val) ? "" : StrUtil.replace(val, ",", "");
    }

    public String setValue(Object val, ExcelField excelField) {
        return StrUtil.isEmpty(val.toString()) ? "" : this.nf.format(val);
    }

    @Override
    public String getDataFormat() {
        return "0.00";
    }
}
