package top.ctong.mall.common.auto;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import top.ctong.mall.common.config.ControllerAdviceImpl;

/**
 * <p>
 * 控制器错误统一处理器
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-21 00:11
 */
@ConditionalOnClass(ControllerAdvice.class)
@Configuration
public class ControllerExceptionHandlerAutoConfiguration {

    @Bean
    public ControllerAdviceImpl controllerAdvice() {
        return new ControllerAdviceImpl();
    }

}
