package com.gj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS","PATCH")
                .allowedHeaders("*")
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/avatar/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/avatar/");
        registry.addResourceHandler("/shoplogo/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/shoplogo/");
        registry.addResourceHandler("/shopimages/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/shopimages/");
        registry.addResourceHandler("/tabulateimages/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/tabulateimages/");
        registry.addResourceHandler("/bg/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/bg/");
        registry.addResourceHandler("/shareimages/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/shareimages/");
    }
};
