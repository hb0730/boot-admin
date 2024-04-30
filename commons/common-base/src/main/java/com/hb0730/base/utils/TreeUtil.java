package com.hb0730.base.utils;

import cn.hutool.core.collection.CollectionUtil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
public class TreeUtil {
    /**
     * 构建树
     *
     * @param nodes .
     * @param <Id>  .
     * @param <T>   .
     * @return .
     */
    public static <Id, T extends Node<T, Id>> List<T> buildTree(List<T> nodes) {
        if (CollectionUtil.isEmpty(nodes)) {
            return null;
        }
        List<T> tree = new ArrayList<>();
        for (T node : nodes) {
            if (node.isRoot()) {
                tree.add(node);
            }
            for (T n : nodes) {
                if (node.getId().equals(n.getParentId())) {
                    if (node.getChildren() == null) {
                        node.setChildren(new ArrayList<>());
                    }
                    node.getChildren().add(n);
                }
            }
        }
        return tree;
    }

    public interface Node<T, Id> {
        /**
         * 获取id
         *
         * @return .
         */
        Id getId();

        /**
         * 获取id
         *
         * @return .
         */
        Id getParentId();

        /**
         * 获取子类
         *
         * @return .
         */
        List<T> getChildren();

        /**
         * 设置子类
         *
         * @param children .
         */
        void setChildren(List<T> children);

        /**
         * 是否是根节点
         *
         * @return .
         */
        @Schema(hidden = true)
        default boolean isRoot() {
            Id id = getParentId();
            return id == null || id.equals(0);
        }
    }
}