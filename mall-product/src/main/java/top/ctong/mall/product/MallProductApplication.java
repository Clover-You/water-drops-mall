package top.ctong.mall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import top.ctong.mall.common.config.RespBodyAdviceConfig;

@MapperScan("top.ctong.mall.product.webApi.dao")
@ImportAutoConfiguration(RespBodyAdviceConfig.class)
@SpringBootApplication
public class MallProductApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MallProductApplication.class, args);
        System.out.println("run");
    }

}
