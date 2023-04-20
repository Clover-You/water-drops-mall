package top.ctong.mall.common.utils;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 统一响应对象
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-01 15:35
 */
@Data
public class R<T> implements Serializable {

    private String code;

    private String message;

    private T data;

    private R(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 响应成功
     *
     * @param code    成功状态码
     * @param message 请求状态消息
     * @param data    数据
     * @return R<T>
     * @author Clover You
     * @date 2023/4/20 21:53
     */
    public static <T> R<T> ok(String code, String message, T data) {
        return new R<>(code, message, data);
    }

    /**
     * 响应成功
     *
     * @param code    成功状态码
     * @param message 请求状态消息
     * @param data    数据
     * @return R<T>
     * @author Clover You
     * @date 2023/4/20 21:53
     */
    public static <T> R<T> ok(int code, String message, T data) {
        return R.ok(Integer.toString(code), message, data);
    }

    /**
     * 响应错误
     *
     * @param code    错误状态吗
     * @param message 错误信息
     * @param data    错误响应数据
     * @return R<T>
     * @author Clover You
     * @date 2023/4/21 00:14
     */
    public static <T> R<T> fail(String code, String message, T data) {
        return new R<>(code, message, data);
    }

    /**
     * 响应错误
     *
     * @param code    错误状态吗
     * @param message 错误信息
     * @return R<Void>
     * @author Clover You
     * @date 2023/4/21 00:15
     */
    public static R<Void> fail(int code, String message) {
        return R.fail(Integer.toString(code), message, null);
    }

}
