package com.hb0730.common.util;

import com.hb0730.common.api.BaseQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
public class PageUtil {
    /**
     * 转换为Pageable
     *
     * @param query 分页查询
     * @param <T>   分页查询
     * @return Pageable
     */
    public static <T extends BaseQuery> Pageable toPage(T query) {
        PageRequest pageRequest = PageRequest.of(
                query.getCurrent() - 1,
                query.getSize()
        );
        if (query.getSorts().isPresent()) {
            pageRequest = pageRequest.withSort(
                    Sort.by(
                            query.getSorts().get()
                    )
            );
        }
        return pageRequest;
    }
}
