package com.hb0730.sys.system.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/16
 */
public interface TreeVO<T> extends Serializable {

    /**
     * 获取子类
     *
     * @return .
     */
    public List<T> getChildren();

    /**
     * 设置子类
     *
     * @param children .
     */
    void setChildren(List<T> children);
}
