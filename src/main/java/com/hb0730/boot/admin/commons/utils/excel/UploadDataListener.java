package com.hb0730.boot.admin.commons.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hb0730.boot.admin.commons.domain.model.domain.ExcelDomain;
import com.hb0730.boot.admin.commons.domain.service.IExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 模板的读取类
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class UploadDataListener<T extends ExcelDomain> extends AnalysisEventListener<T> {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(UploadDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;

    private List<T> list = new ArrayList<T>();

    private IExportService service;

    public UploadDataListener(IExportService service) {
        this.service = service;
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        service.upload(list);
        LOGGER.info("存储数据库成功！");
    }
}
