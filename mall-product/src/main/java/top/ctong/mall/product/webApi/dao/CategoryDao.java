package top.ctong.mall.product.webApi.dao;

import org.apache.ibatis.annotations.Mapper;
import top.ctong.mall.common.models.CategoryTree;
import top.ctong.mall.common.models.entity.CategoryEntity;

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

}
