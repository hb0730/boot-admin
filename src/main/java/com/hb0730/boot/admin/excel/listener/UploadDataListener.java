package com.hb0730.boot.admin.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hb0730.boot.admin.domain.model.excel.ExcelDomain;
import com.hb0730.boot.admin.domain.service.base.ISuperPoiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板的读取类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class UploadDataListener<T extends ExcelDomain> extends AnalysisEventListener<T> {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(UploadDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;
    private List<T> list = new ArrayList<T>();

    @SuppressWarnings({"rawtypes"})
    private ISuperPoiService service;

    @SuppressWarnings({"rawtypes"})
    public UploadDataListener(ISuperPoiService service) {
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
