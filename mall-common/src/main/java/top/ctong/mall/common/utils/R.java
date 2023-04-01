package top.ctong.mall.common.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 统一响应对象
 * </p>
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-01 15:35
 */
@Data
public class R<T> implements Serializable {

    private String code;

    private String message;

    private T data;

    private R() {
    }

}
