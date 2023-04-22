package top.ctong.mall.product.webApi.category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ctong.mall.common.models.CategoryTree;
import top.ctong.mall.common.models.TreeNode;
import top.ctong.mall.common.models.dto.product.CategorySaveDto;
import top.ctong.mall.common.models.entity.CategoryEntity;
import top.ctong.mall.common.utils.BeanUtils;
import top.ctong.mall.common.utils.RespStatus;
import top.ctong.mall.product.exception.CategoryException;
import top.ctong.mall.product.exception.CategorySaveException;
import top.ctong.mall.product.exception.CategoryUpdateException;
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
        boolean nameExist = nameExistUnderSameLevel(dto.getName(), dto.getParentCid(), dto.getCatLevel(), null);
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
            List<Long> brotherId = getGreaterSortLeafList(dto.getParentCid(), dto.getCatLevel(), sort, null, null);
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
     * @param ignore 忽略查询的id
     * @return boolean
     * @author Clover You
     * @date 2023/4/20 23:02
     */
    @Override
    public boolean nameExistUnderSameLevel(String name, Long pid, Integer level, Long ignore) {
        return categoryDao.nameExistUnderSameLevel(name, pid, level, ignore);
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
     * @param ignore 忽略指定id
     * @return List<Long>
     * @author Clover You
     * @date 2023/4/20 23:16
     */
    @Override
    public List<Long> getGreaterSortLeafList(Long pId, Integer level, Integer sort, Integer endSort, Long ignore) {
        return categoryDao.getGreaterSortLeafList(pId, level, sort, endSort, ignore);
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
        if (ids.size() == 0) {
            return 0;
        }
        return categoryDao.updateSortByStep(ids, step);
    }


    /**
     * 通过分类id获取分类信息
     *
     * @param catId 分类id
     * @return CategoryEntity
     * @author Clover You
     * @date 2023/4/22 21:08
     */
    @Override
    public CategoryEntity info(Long catId) {
        CategoryEntity category = categoryDao.info(catId);
        if (category == null) {
            throw new CategoryException(RespStatus.PRODUCT_CATEGORY_QUERY_CATEGORY_NOT_EXIST);
        }
        return category;
    }

    /**
     * 修改分类信息
     *
     * @param category 分类
     * @return int
     * @author Clover You
     * @date 2023/4/22 23:08
     */
    @Override
    public int update(CategoryEntity category) {
        Long catId = category.getCatId();
        CategoryEntity record = info(catId);

        Long pId = Optional.ofNullable(category.getParentCid()).orElse(record.getParentCid());
        // 检查父级是否存在
        if (pId > 0 && !Objects.equals(pId, record.getParentCid()) && !exists(pId)) {
            throw new CategoryUpdateException(RespStatus.PRODUCT_CATEGORY_UPDATE_PARENT_NO_FOUND);
        }

        // 如果名称有变，检查名称是否存在
        String name = Optional.ofNullable(category.getName()).orElse(record.getName()).trim();
        Integer level = Optional.ofNullable(category.getCatLevel()).orElse(record.getCatLevel());
        if (!Objects.equals(name, record.getName().trim()) && nameExistUnderSameLevel(name, pId, level, catId)) {
            throw new CategoryUpdateException(RespStatus.PRODUCT_CATEGORY_UPDATE_ALREADY_EXIST_NAME);
        }


        // 获取所有节点数
        Integer sort = Optional.ofNullable(category.getSort()).orElse(record.getSort());
        if (!Objects.equals(sort, record.getSort())) {
            int leafCount = getLeafCount(pId, level);

            if (sort > leafCount) {
                // 如果大于所有节点统计数，那么证明他是最后一个节点
                category.setSort(leafCount);
            } else {
                // 如果sort - record 是大于0，那么证明需要把后面所有的分类都移动
                int offset = sort - record.getSort();
                Integer startSort = offset > 0 ? record.getSort() : sort;
                Integer endSort = offset > 0 ? sort : record.getSort();

                List<Long> leafList = getGreaterSortLeafList(pId, level, startSort, endSort, catId);

                updateSortByStep(leafList, offset > 0 ? -1 : 1);
            }
        }

        // 排序
        return categoryDao.update(category);
    }

}
