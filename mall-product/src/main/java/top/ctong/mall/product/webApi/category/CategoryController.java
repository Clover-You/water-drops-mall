package top.ctong.mall.product.webApi.category;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ctong.mall.common.models.CategoryTree;

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
@RequestMapping("/product/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/tree")
    public List<CategoryTree> tree() {
        return categoryService.listWithTree();
    }

}
