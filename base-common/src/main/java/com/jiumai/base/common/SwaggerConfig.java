package com.jiumai.base.common;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * SwaggerConfig
 */
@Configuration
@EnableKnife4j
@Profile({"dev", "test"})
public class SwaggerConfig {


    @Bean(value = "bizApi")
    public Docket bizApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .groupName("1-业务管理API")
                .apiInfo(apiInfo())
                .select()
                .apis(basePackage("com.jiumai.base.biz.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean(value = "smApi")
    public Docket smApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .groupName("2-系统管理API")
                .apiInfo(apiInfo())
                .select()
                .apis(basePackage("com.jiumai.base.sm.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean(value = "commonApi")
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .groupName("3-工具类api")
                .apiInfo(apiInfo())
                .select()
                .apis(basePackage("com.jiumai.base.common.core.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    /**
     * 文档信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("管理系统接口API")
                .description("api接口文档")
                .termsOfServiceUrl("http://localhost:6001/xsd-server-core")
                .version("1.0.0")
                .build();
    }

    private static Predicate<RequestHandler> basePackage(String... basePackages) {
        return input -> declaringClass(input).transform(handlerPackage(basePackages)).or(true);
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }

    private static Function<Class<?>, Boolean> handlerPackage(String[] basePackage) {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }
}