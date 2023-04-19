package top.ctong.mall.product.webApi.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.ctong.mall.common.models.CategoryTree;
import top.ctong.mall.common.config.valid.UpdateGroup;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 分类前端控制器
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-08 18:44
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(CategoryController.class);

    /**
     * 分类业务服务
     */
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 查询所有商品菜单
     *
     * @return List<CategoryTree>
     * @author Clover You
     * @date 2023/4/19 20:58
     */
    @RequestMapping("/tree")
    public List<CategoryTree> tree() {
        return categoryService.listWithTree();
    }

    /**
     * 通过分类id删除分类
     *
     * @param catIds 分类id
     * @author Clover You
     * @date 2023/4/19 20:58
     */
    @DeleteMapping("/delete")
    public void delete(@RequestBody Integer[] catIds) {
        categoryService.delete(Arrays.asList(catIds));
    }

    /**
     * 新增分类
     *
     * @param dto 分类信息
     * @author Clover You
     * @date 2023/4/19 21:14
     */
    @PostMapping("/save")
    public void save(@RequestBody @Validated({UpdateGroup.class}) CategorySaveDto dto) {

    }

}
