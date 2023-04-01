package top.ctong.mall.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * mybatis plus配置
 * </p>
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-01 15:41
 */
@MapperScan("top.ctong.mall.*.dao")
@ConditionalOnMissingBean({
    MyBatisConfiguration.class,
    java.sql.Driver.class
})
@Configuration
public class MyBatisConfiguration {

}
