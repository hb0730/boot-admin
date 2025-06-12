package com.hb0730.base.utils;

import cn.hutool.core.collection.CollUtil;

import java.util.List;

/**
 * 树工具
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/23
 */
public class TreeUtil {

    /**
     * 构建树
     *
     * @param nodes 节点
     * @param <T>   节点类型
     * @param <Id>  id类型
     * @return .
     */
    public static <T extends Node<T, Id>, Id> List<T> build(List<T> nodes) {
        Tree<T, Id> tree = new Tree<>(nodes);
        return tree.build();
    }

    /**
     * 节点
     *
     * @param <T>
     * @param <Id>
     */
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
         * <p>
         * <p>如果父节点id为空或者为0，则为根节点</p>
         *
         * @return .
         */
        default boolean isRoot() {
            Id id = getParentId();
            return id == null || StrUtil.isBlank(id.toString()) || id.equals(0);
        }
    }


    static class Tree<T extends Node<T, Id>, Id> {
        private final List<T> nodes;

        public Tree(List<T> nodes) {
            this.nodes = nodes;
        }

        public List<T> build() {
            List<T> tree = CollUtil.newArrayList();
            for (T node : nodes) {
                if (node.isRoot()) {
                    tree.add(findChildren(node));
                }
            }
            return tree;
        }

        private T findChildren(T node) {
            for (T child : nodes) {
                if (node.getId().equals(child.getParentId())) {
                    if (node.getChildren() == null) {
                        node.setChildren(CollUtil.newArrayList());
                    }
                    node.getChildren().add(findChildren(child));
                }
            }
            return node;
        }
    }
}
