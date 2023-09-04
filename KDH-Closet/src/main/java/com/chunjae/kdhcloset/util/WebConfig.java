package com.chunjae.kdhcloset.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private String resourcePath = "/upload/**"; // view 접근 경로

    private String savePath = "file:///C:/imgs/"; // 실제 파일 저장 경로(win)

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);

        registry.addResourceHandler("/css/**") // CSS 파일을 어떤 URL에서 제공할 것인지 지정
                .addResourceLocations("classpath:/static/css/"); // 실제 파일 경로 설정

    }


}
