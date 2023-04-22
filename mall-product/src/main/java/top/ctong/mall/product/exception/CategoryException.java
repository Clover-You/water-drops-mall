package top.ctong.mall.product.exception;

import top.ctong.mall.common.exception.BaseException;
import top.ctong.mall.common.utils.RespStatus;

/**
 * <p>
 *
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-22 21:10
 */
public class CategoryException extends BaseException {

    private static final long serialVersionUID = 6818247304921016623L;

    public CategoryException(String message) {
        super(message);
    }

    public CategoryException(RespStatus rs) {
        super(rs);
    }

    public CategoryException(RespStatus rs, String message) {
        super(rs, message);
    }

}
