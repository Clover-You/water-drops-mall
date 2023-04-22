package top.ctong.mall.product.exception;

import top.ctong.mall.common.utils.RespStatus;

/**
 * <p>
 * 保存分类异常
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-20 22:19
 */
public class CategorySaveException extends CategoryException {

    private static final long serialVersionUID = 2238303234783057918L;

    public CategorySaveException(String message) {
        super(message);
    }

    public CategorySaveException(RespStatus rs) {
        super(rs);
    }

    public CategorySaveException(RespStatus rs, String message) {
        super(rs, message);
    }

}
