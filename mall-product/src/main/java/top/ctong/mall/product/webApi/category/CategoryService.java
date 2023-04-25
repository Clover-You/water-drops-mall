package top.ctong.mall.product.webApi.category;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import top.ctong.mall.common.models.CategoryTree;
import top.ctong.mall.common.models.dto.product.CategorySaveDto;
import top.ctong.mall.common.models.entity.CategoryEntity;

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
@Service
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
     *
     * @param ids 分类id列表
     * @return int
     * @author Clover You
     * @date 2023/4/18 23:45
     */
    int delete(@NotNull List<Integer> ids);

    /**
     * 保存分类信息
     *
     * @param dto 分类信息
     * @return int
     * @author Clover You
     * @date 2023/4/20 22:08
     */
    long save(@NotNull CategorySaveDto dto);

    /**
     * 通过分类id查询分类是否存在
     *
     * @param catId 分类id
     * @return boolean
     * @author Clover You
     * @date 2023/4/20 22:15
     */
    boolean exists(@NotNull Long catId);

    /**
     * 在同级分类下是否存在指定分类名称
     *
     * @param name   分类名称
     * @param pid    父级id
     * @param level  层级id
     * @param ignore 查询的分类id
     * @return boolean
     * @author Clover You
     * @date 2023/4/20 23:02
     */
    boolean nameExistUnderSameLevel(@NotNull String name, @NotNull Long pid, @NotNull Integer level, Long ignore);

    /**
     * 获取同级节点的数量
     *
     * @param pId   父节点
     * @param level 节点层级
     * @return int
     * @author Clover You
     * @date 2023/4/20 23:11
     */
    int getLeafCount(@NotNull Long pId, @NotNull Integer level);

    /**
     * 获取大于指定排序值的分类id
     *
     * @param pId     父分类id
     * @param level   层级
     * @param sort    排序
     * @param endSort 排序结束范围
     * @param ignore  忽略指定id
     * @return List<Long>
     * @author Clover You
     * @date 2023/4/20 23:16
     */
    List<Long> getGreaterSortLeafList(@NotNull Long pId, @NotNull Integer level, @NotNull Integer sort,
                                      Integer endSort, Long ignore);

    /**
     * 通过指定步长修改指定分类的排序
     *
     * @param ids  分类列表
     * @param step 步长
     * @return int
     * @author Clover You
     * @date 2023/4/20 23:53
     */
    int updateSortByStep(@NotNull List<Long> ids, @NotNull int step);

    /**
     * 通过分类id获取分类信息
     *
     * @param catId 分类id
     * @return CategoryEntity
     * @author Clover You
     * @date 2023/4/22 21:08
     */
    CategoryEntity info(@NotNull Long catId);

    /**
     * 修改分类信息
     *
     * @param category 分类
     * @return int
     * @author Clover You
     * @date 2023/4/22 23:08
     */
    int update(@NotNull CategoryEntity category);

    /**
     * 修改分类排序
     *
     * @param catId 分类id
     * @param sort  排序
     * @return int
     * @author Clover You
     * @date 2023/4/25 20:52
     */
    int updateSort(@NotNull Long catId, @NotNull Integer sort);

}
