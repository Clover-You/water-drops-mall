package top.ctong.mall.product.webApi.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.ctong.mall.common.models.CategoryTree;
import top.ctong.mall.common.models.entity.CategoryEntity;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 分类
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-08 19:14
 */
@Mapper
public interface CategoryDao {

    /**
     * 获取所有分类列表
     *
     * @return List<CategoryEntity>
     * @author Clover You
     * @date 2023/4/8 19:18
     */
    List<CategoryEntity> list();

    List<CategoryTree> tree();

    /**
     * 通过分类id批量删除分类
     *
     * @param ids 分类id
     * @return int
     * @author Clover You
     * @date 2023/4/18 23:47
     */
    int delete(@NotNull List<Integer> ids);

    /**
     * 保存新增
     *
     * @param category 分类信息
     * @return long
     * @author Clover You
     * @date 2023/4/20 23:21
     */
    long save(@NotNull @Param("category") CategoryEntity category);

    /**
     * 查询分类是否存在
     *
     * @param catId 分类id
     * @return boolean
     * @author Clover You
     * @date 2023/4/20 23:30
     */
    boolean exists(@Param("catId") @NotNull Long catId);

    /**
     * 查询同层级下是否存在指定名称
     *
     * @param name  分类名称
     * @param pid   父级id
     * @param level 分类层级
     * @return boolean
     * @author Clover You
     * @date 2023/4/20 23:35
     */
    boolean nameExistUnderSameLevel(@NotNull @Param("name") String name, @NotNull @Param("pid") Long pid,
                                    @NotNull @Param("level") Integer level);

    /**
     * 查询所有同级的节点数
     *
     * @param pId   父id
     * @param level 层级
     * @return int
     * @author Clover You
     * @date 2023/4/20 23:42
     */
    int getLeafCount(@NotNull @Param("pId") Long pId, @NotNull @Param("level") Integer level);

    /**
     * 获取同级菜单排序大于指定排序的菜单id
     *
     * @param pId   父级id
     * @param level 层级
     * @param sort  排序
     * @return List<Long>
     * @author Clover You
     * @date 2023/4/20 23:45
     */
    List<Long> getGreaterSortLeafList(@NotNull @Param("pId") Long pId, @NotNull @Param("level") Integer level,
                                      @NotNull @Param("sort") Integer sort);

    /**
     * 根据指定步长修改分类排序
     *
     * @param ids  分类列表
     * @param step 步长
     * @return int
     * @author Clover You
     * @date 2023/4/20 23:55
     */
    int updateSortByStep(@NotNull @Param("ids") List<Long> ids, @NotNull @Param("step") int step);

}
