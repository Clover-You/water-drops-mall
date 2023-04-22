package top.ctong.mall.product.exception;

import top.ctong.mall.common.utils.RespStatus;

/**
 * <p>
 * 修改分类信息异常
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-22 23:17
 */
public class CategoryUpdateException extends CategoryException {

    private static final long serialVersionUID = 1981321199348223220L;

    public CategoryUpdateException(String message) {
        super(message);
    }

    public CategoryUpdateException(RespStatus rs) {
        super(rs);
    }

    public CategoryUpdateException(RespStatus rs, String message) {
        super(rs, message);
    }

}
