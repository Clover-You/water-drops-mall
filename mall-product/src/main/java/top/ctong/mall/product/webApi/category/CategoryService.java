package top.ctong.mall.product.webApi.category;

import top.ctong.mall.common.models.CategoryTree;
import top.ctong.mall.common.models.TreeNode;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 分类服务
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-08 18:46
 */
public interface CategoryService {

    /**
     * 获取所有分类列表
     *
     * @return List<CategoryEntity>
     * @author Clover You
     * @date 2023/4/8 19:03
     */
    List<CategoryTree> listWithTree();

    /** 
     * 通过分类id批量删除分类
     * @param ids 分类id列表
     * @return int 
     * @author Clover You 
     * @date 2023/4/18 23:45
     */
    int delete(@NotNull List<Integer> ids);

}
