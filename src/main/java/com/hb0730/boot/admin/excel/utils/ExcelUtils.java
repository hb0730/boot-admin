package com.hb0730.boot.admin.excel.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.hb0730.boot.admin.commons.utils.WebFilenameUtils;
import com.hb0730.boot.admin.domain.model.excel.ExcelDomain;
import org.springframework.lang.NonNull;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * easyexcel 操作
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class ExcelUtils {
    private static final String CONTENT_TYPE = "application/vnd.ms-excel";

    /**
     * web导出
     *
     * @param response      httpServlet response
     * @param data          数据
     * @param excelTypeEnum 导出excel类型
     * @param clazz         head
     * @param <T>           excel domain 泛型
     */
    @SuppressWarnings({"unchecked"})
    public static <T extends ExcelDomain> void writeWeb(@NonNull HttpServletResponse response, @NonNull Map<String, Object> data, ExcelTypeEnum excelTypeEnum, Class<T> clazz) throws IOException {
        String filename = data.get(ExcelConstant.FILE_NAME).toString();
        List<T> datas = (List<T>) data.get(ExcelConstant.DATA_LIST);
        setResponseHeader(response, filename, excelTypeEnum);
        ServletOutputStream outputStream = response.getOutputStream();
        write(outputStream, datas, excelTypeEnum, clazz);
    }

    /**
     * web 多sheet导出
     *
     * @param response      响应
     * @param data          数据
     * @param excelTypeEnum 类型
     * @param clazz         head
     * @param <T>           excel domain 泛型
     */
    @SuppressWarnings({"unchecked"})
    public static <T extends ExcelDomain> void writeSheetWeb(@NonNull HttpServletResponse response, @NonNull Map<String, Object> data, ExcelTypeEnum excelTypeEnum, Class<T> clazz) throws IOException {
        String filename = data.get(ExcelConstant.FILE_NAME).toString();
        setResponseHeader(response, filename, excelTypeEnum);
        Map<String, List<T>> maps = (Map<String, List<T>>) data.get(ExcelConstant.MAP_LIST);
        writeSheet(response.getOutputStream(), maps, excelTypeEnum, clazz);
    }

    /**
     * 单页签
     *
     * @param outputStream  stream
     * @param data          数据
     * @param excelTypeEnum 导出excel类型
     * @param clazz         head
     * @param <T>           excel domain 泛型
     */
    public static <T extends ExcelDomain> void write(@NonNull OutputStream outputStream, @NonNull List<T> data, ExcelTypeEnum excelTypeEnum, Class<T> clazz) throws IOException {
        excelTypeEnum = excelTypeEnum == null ? ExcelTypeEnum.XLS : excelTypeEnum;
        EasyExcel.write(outputStream, clazz).excelType(excelTypeEnum).sheet("sheet1").doWrite(data);
        outputStream.flush();
    }

    /**
     * 多页签导出
     *
     * @param outputStream  out流
     * @param data          多页签数据key为sheet名称
     * @param excelTypeEnum excel类型
     * @param clazz         head
     * @param <T>           excel domain 泛型
     */
    public static <T extends ExcelDomain> void writeSheet(@NonNull OutputStream outputStream, Map<String, List<T>> data, ExcelTypeEnum excelTypeEnum, Class<T> clazz) {
        excelTypeEnum = excelTypeEnum == null ? ExcelTypeEnum.XLS : excelTypeEnum;
        ExcelWriter writer = EasyExcel.write(outputStream, clazz).excelType(excelTypeEnum).build();
        if (!CollectionUtil.isEmpty(data)) {
            data.forEach((k, v) -> {
                WriteSheet sheet = EasyExcel.writerSheet(k).build();
                writer.write(v, sheet);
            });
        }
        writer.finish();
    }

    /**
     * 获取output流
     *
     * @param excelProperties excel配置
     * @return 输出流
     */
    @NonNull
    public static OutputStream getOutputStream(@NonNull ExcelProperties excelProperties) throws FileNotFoundException {
        String path = excelProperties.getPath();
        String fileName = excelProperties.getFileName();
        ExcelTypeEnum excelType = excelProperties.getExcelType();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String sb = path +
            "/" +
            fileName +
            "-" +
            format.format(new Date()) +
            "." + excelType.getValue();
        return new FileOutputStream(sb);
    }

    /**
     * 设置响应头信息
     *
     * @param response      响应信息
     * @param filename      文件名称
     * @param excelTypeEnum 文件类型
     */
    private static void setResponseHeader(HttpServletResponse response, String filename, ExcelTypeEnum excelTypeEnum) throws UnsupportedEncodingException {
        response.setHeader("content-type", CONTENT_TYPE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        if (!filename.endsWith(ExcelTypeEnum.XLSX.getValue()) && !filename.endsWith(ExcelTypeEnum.XLS.getValue())) {
            filename = filename + excelTypeEnum.getValue();
        }
        filename = WebFilenameUtils.disposition(filename);
        response.setHeader("content-disposition", filename);
    }
}
