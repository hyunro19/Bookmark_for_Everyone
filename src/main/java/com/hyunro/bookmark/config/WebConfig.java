package com.hyunro.bookmark.config;

import com.hyunro.bookmark.config.jwt.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    private static final String[] EXCLUDE_PATHS = {
//            "",
//            "/**",
//            "/member/**",
//            "/error/**"
    };

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATHS);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .exposedHeaders("jwt_token") // CORS일 때, 해당 헤더를 읽을 수 있게 처리해준다.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "HEAD");
    }
}