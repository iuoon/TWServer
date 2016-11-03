package com.iuoon.tw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  spring boot 常用注解说明
 * （1）@RestController和@Controller指定一个类，作为控制器的注解
 * （2）@RequestMapping方法级别的映射注解，这一个用过Spring MVC的小伙伴相信都很熟悉
 * （3）@EnableAutoConfiguration和@SpringBootApplication是类级别的注解，根据maven依赖的jar来自动猜测完成正确的spring的对应配置，只要引入了spring-boot-starter-web的依赖，默认会自动配置Spring MVC和tomcat容器
 * （4）@Configuration类级别的注解，一般这个注解，我们用来标识main方法所在的类
 * （5）@ComponentScan类级别的注解，自动扫描加载所有的Spring组件包括Bean注入，一般用在main方法所在的类上
 * （6）@ImportResource类级别注解，当我们必须使用一个xml的配置时，使用@ImportResource和@Configuration来标识这个文件资源的类。
 * （7）@Autowired注解，一般结合@ComponentScan注解，来自动注入一个Service或Dao级别的Bean
 * （8）@Component类级别注解，用来标识一个组件，比如我自定了一个filter，则需要此注解标识之后，Spring Boot才会正确识别。
 *  Created by mwuyz on 2016/8/30.
 */

@SpringBootApplication //等同于 @Configuration @EnableAutoConfiguration @ComponentScanpublic
public class Application extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {
    //启动Spring Boot入口
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8081);
    }
}
