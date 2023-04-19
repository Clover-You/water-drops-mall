package top.ctong.mall.common.models;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 树型结构
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-08 20:52
 */
public interface TreeNode<T, P> extends Serializable {

    /**
     * 获取主键id
     */
    P getId();

    /**
     * 设置主键id
     */
    void setId(P id);

    /**
     * 获取父级id
     */
    P getParentId();

    /**
     * 设置父级id
     */
    void setParentId(P id);

    /**
     * 子节点列表
     *
     * @param children 子节点列表
     */
    void setChildren(List<T> children);

    /**
     * 子节点列表
     */
    List<T> getChildren();

}
