package top.ctong.mall.common.auto;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.ctong.mall.common.config.RespBodyAdviceConfig;

/**
 * <p>
 * 统一返回响应自动配置
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-19 22:54
 */
@ConditionalOnClass(ResponseBodyAdvice.class)
@Configuration
public class RespBodyAdviceAutoConfiguration {

    @Bean
    public RespBodyAdviceConfig respBodyAdvice() {
        return new RespBodyAdviceConfig();
    }
}
