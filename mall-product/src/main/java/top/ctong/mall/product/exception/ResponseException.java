package top.ctong.mall.product.exception;

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
public class ResponseException extends RuntimeException implements Serializable {

    public ResponseException(String message) {
        super(message);
    }

}
