package top.ctong.mall.common.auto;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import top.ctong.mall.common.config.GlobalProperties;

/**
 * <p>
 * 全局设置属性
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-20 22:25
 */
@Configuration
@EnableConfigurationProperties(GlobalProperties.class)
public class GlobalPropertiesAutoConfiguration {}
