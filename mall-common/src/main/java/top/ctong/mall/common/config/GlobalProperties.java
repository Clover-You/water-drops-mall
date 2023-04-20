package top.ctong.mall.common.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * <p>
 * 全局配置属性
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-20 22:26
 */
@Setter(AccessLevel.PROTECTED)
@Getter
@ConfigurationProperties(prefix = "water.config")
public class GlobalProperties implements Serializable {

    private static final long serialVersionUID = -3526119914130735946L;

    /**
     * 请求状态码前缀
     */
    private String httpStatusPrefix;
}
