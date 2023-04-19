package top.ctong.mall.common.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.ctong.mall.common.models.entity.CategoryEntity;

import java.util.List;

/**
 * <p>
 * 分类树结构
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-08 20:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryTree extends CategoryEntity implements TreeNode<CategoryTree, Long> {

    private Long id;

    private Long parentId;

    private List<CategoryTree> children;

}
