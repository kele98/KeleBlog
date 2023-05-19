package top.aikele.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @projectName: KeleBlog
 * @package: top.aikele.config
 * @className: SwaggerConfig
 * @author: Kele
 * @description: TODO
 * @date: 2023/4/24 2:23
 * @version: 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.aikele"))
                .paths(PathSelectors.any())
                .build();


    }
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Kele`s Blog")
                .description("可乐的博客项目")
                .termsOfServiceUrl("") // 用于定义服务的域名
                .version("0.1")
                .build();
    }

}
