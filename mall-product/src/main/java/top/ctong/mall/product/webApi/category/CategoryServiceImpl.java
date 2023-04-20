package top.ctong.mall.product.webApi.category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ctong.mall.common.models.CategoryTree;
import top.ctong.mall.common.models.TreeNode;
import top.ctong.mall.common.models.dto.product.CategorySaveDto;
import top.ctong.mall.common.models.entity.CategoryEntity;
import top.ctong.mall.common.utils.BeanUtils;
import top.ctong.mall.common.utils.RespStatus;
import top.ctong.mall.product.exception.CategorySaveException;
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

    /**
     * 保存分类信息
     *
     * @param dto 分类信息
     * @return int
     * @author Clover You
     * @date 2023/4/20 22:08
     */
    @Transactional
    @Override
    public long save(CategorySaveDto dto) {
        // 如果存在父级分类，那么检查父级分类是否存在
        if (dto.getParentCid() > 0) {
            boolean existParen = exists(dto.getParentCid());
            if (!existParen) {
                throw new CategorySaveException(RespStatus.PRODUCT_CATEGORY_SAVE_NOT_EXIST);
            }
        }

        dto.setName(
            dto.getName().trim()
        );

        // 检查同级下是否存在相同的分类名称
        boolean nameExist = nameExistUnderSameLevel(dto.getName(), dto.getParentCid(), dto.getCatLevel());
        if (nameExist) {
            throw new CategorySaveException(RespStatus.PRODUCT_CATEGORY_SAVE_NAME_ALREADY_EXISTS);
        }
        // 如果分类排序重复，那么就分类及之后的所有分类全部让位
        Integer sort = dto.getSort();
        // 没有指定排序，那么默认在最后
        if (sort == null) {
            // 查询所有同级的节点数量作为排序
            dto.setSort(
                getLeafCount(dto.getParentCid(), dto.getCatLevel()) + 1
            );
        } else {
            // 获取所有排序大于当前菜单排序的菜单列表
            List<Long> brotherId = getGreaterSortLeafList(dto.getParentCid(), dto.getCatLevel(), sort);
            if (brotherId.size() > 0) {
                // 如果存在这些菜单，那么查询设置位置
                updateSortByStep(brotherId, 1);
            }
        }

        CategoryEntity category = new CategoryEntity();
        BeanUtils.copyProperties(dto, category);

        return categoryDao.save(category);
    }

    /**
     * 通过分类id查询分类是否存在
     *
     * @param catId 分类id
     * @return boolean
     * @author Clover You
     * @date 2023/4/20 22:15
     */
    @Override
    public boolean exists(Long catId) {
        return categoryDao.exists(catId);
    }

    /**
     * 在同级分类下是否存在指定分类名称
     *
     * @param name  分类名称
     * @param pid   父级id
     * @param level 层级id
     * @return boolean
     * @author Clover You
     * @date 2023/4/20 23:02
     */
    @Override
    public boolean nameExistUnderSameLevel(String name, Long pid, Integer level) {
        return categoryDao.nameExistUnderSameLevel(name, pid, level);
    }

    /**
     * 获取同级节点的数量
     *
     * @param pId   父节点
     * @param level 节点层级
     * @return int
     * @author Clover You
     * @date 2023/4/20 23:11
     */
    @Override
    public int getLeafCount(Long pId, Integer level) {
        return categoryDao.getLeafCount(pId, level);
    }

    /**
     * 获取大于指定排序值的分类id
     *
     * @param pId   父分类id
     * @param level 层级
     * @param sort  排序
     * @return List<Long>
     * @author Clover You
     * @date 2023/4/20 23:16
     */
    @Override
    public List<Long> getGreaterSortLeafList(Long pId, Integer level, Integer sort) {
        return categoryDao.getGreaterSortLeafList(pId, level, sort);
    }

    /**
     * 通过指定步长修改指定分类的排序
     *
     * @param ids  分类列表
     * @param step 步长
     * @return int
     * @author Clover You
     * @date 2023/4/20 23:53
     */
    @Override
    public int updateSortByStep(List<Long> ids, int step) {
        return categoryDao.updateSortByStep(ids, step);
    }

}
