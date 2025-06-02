package com.my.classtask.springboot.common;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置
 */
@Configuration  //将其内部定义的 @Bean 方法所返回的对象注册到 Spring IoC 容器中
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1 设置访问源地址
        corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头（Header）可以在跨域请求中使用
        corsConfiguration.addAllowedMethod("*"); // 3 设置访问源请求方法 哪些 HTTP 请求方法（如 GET, POST, PUT, DELETE, OPTIONS 等
        source.registerCorsConfiguration("/**", corsConfiguration); // 4 对接口配置跨域设置 意味着你的后端所有 API 接口都将允许跨域访问
        return new CorsFilter(source); //CorsFilter 是 Spring Security 或 Spring MVC 提供的过滤器，它会在每个 HTTP 请求到达你的 Controller 之前拦截它们，并根据你配置的 CORS 规则来处理跨域相关的 HTTP 头
    }
}