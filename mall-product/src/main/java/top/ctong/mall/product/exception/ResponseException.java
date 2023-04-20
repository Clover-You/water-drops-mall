package top.ctong.mall.product.exception;

import top.ctong.mall.common.exception.BaseException;
import top.ctong.mall.common.utils.RespStatus;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 业务异常
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-20 22:17
 */
public class ResponseException extends BaseException {

    private static final long serialVersionUID = 5682204351226980940L;

    public ResponseException(String message) {
        this(null, message);
    }

    public ResponseException(RespStatus rs) {
        this(rs, rs.getMessage());
    }

    public ResponseException(RespStatus rs, String message) {
        super(rs, message);
    }

}
