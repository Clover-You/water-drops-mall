package top.ctong.mall.common.exception;

import top.ctong.mall.common.utils.RespStatus;

/**
 * <p>
 * 业务异常
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-20 22:17
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -2564363246900856607L;

    private final RespStatus rs;

    public RespStatus getRs() {
        return this.rs;
    }

    public BaseException(String message) {
        this(null, message);
    }

    public BaseException(RespStatus rs) {
        this(rs, rs.getMessage());
    }

    public BaseException(RespStatus rs, String message) {
        super(message);
        this.rs = rs;
    }

}
