package top.ctong.mall.common.utils;

import top.ctong.mall.common.models.TreeNode;

import java.util.*;

/**
 * <p>
 * java 对象操作工具
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-08 21:04
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    public static <T> List<T> treeify(List<? extends TreeNode<? extends T, ?>> list, Object... rootFlag) {
//        int TOTAL_SIZE = list.size();
//        int capacity = TOTAL_SIZE > 100 ? TOTAL_SIZE / 2 : TOTAL_SIZE;
//        Map<Object, TreeNode<? extends T, ?>> root = new HashMap<>(capacity);
//        Map<Object, TreeNode<? extends T, ?>> leaf = new HashMap<>(capacity);
//
//        for (TreeNode<? extends T, ?> item : list) {
//            Map<Object, TreeNode<? extends T, ?>> target = leaf;
//            for (Object flag : rootFlag) {
//                if (item.getParentId() == null || Objects.equals(flag, item.getParentId())) {
//                    target = root;
//                }
//            }
//            target.put(item.getId(), item);
//        }
//
//        TreeNode<? extends T, ?> current = null;
//        Object parentId = null;
//
//        for (TreeNode<? extends T, ?> item : leaf.values()) {
//            if ((current = root.get(item.getParentId())) != null) {
//                List<? extends TreeNode<? extends T, ?>> children = null;
//                if ((children = current.getChildren() )== null) {
//                    current.setChildren(current.getChildren() );
//                }
//
//            }
//        }
        return new ArrayList<>();
    }

}
