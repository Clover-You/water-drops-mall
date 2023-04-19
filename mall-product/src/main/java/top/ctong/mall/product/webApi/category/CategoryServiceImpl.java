package top.ctong.mall.product.webApi.category;

import org.springframework.stereotype.Service;
import top.ctong.mall.common.models.CategoryTree;
import top.ctong.mall.common.models.TreeNode;
import top.ctong.mall.product.webApi.dao.CategoryDao;

import java.util.*;

/**
 * <p>
 * 分类
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-08 18:46
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    /**
     * 获取所有分类列表
     *
     * @return List<CategoryEntity>
     * @author Clover You
     * @date 2023/4/8 19:03
     */
    @Override
    public List<CategoryTree> listWithTree() {
        List<CategoryTree> list = categoryDao.tree();
        return categoryListWithTree(list, 0L);
    }

    /**
     * 通过分类id批量删除分类
     *
     * @param ids 分类id列表
     * @return int
     * @author Clover You
     * @date 2023/4/18 23:45
     */
    @Override
    public int delete(List<Integer> ids) {
        // todo 检查分类是否在别处被引用了
        return categoryDao.delete(ids);
    }

    private <T extends TreeNode<T, ?>> List<T> categoryListWithTree(List<T> list, Object rootId) {
        Map<Object, List<T>> tem = new HashMap<>(list.size());
        Map<Object, T> root = new HashMap<>(list.size());

        for (T it : list) {
            if (it.getChildren() == null) {
                it.setChildren(new ArrayList<>());
            }

            if (!tem.containsKey(it.getId())) {
                tem.put(it.getId(), it.getChildren());
            }

            if (it.getParentId() == null || Objects.equals(rootId, it.getParentId())) {
                root.put(it.getId(), it);
                continue;
            }

            List<T> ts = tem.computeIfAbsent(it.getParentId(), k -> new ArrayList<>());
            ts.add(it);
        }

        return new ArrayList<>(root.values());
    }


}
