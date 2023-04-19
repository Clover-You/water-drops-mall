package top.ctong.mall.product.webApi.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import top.ctong.mall.common.models.CategoryTree;

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

    private Logger log = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/tree")
    public List<CategoryTree> tree() {
        return categoryService.listWithTree();
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Integer[] catIds) {
        categoryService.delete(Arrays.asList(catIds));
    }

}
