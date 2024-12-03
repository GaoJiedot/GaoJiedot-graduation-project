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
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS","PATCH")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/avatar/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/avatar/");
        registry.addResourceHandler("/shopLogo/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/shopLogo/");
        registry.addResourceHandler("/shopImages/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/shopImages/");
        registry.addResourceHandler("/tabulateImages/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/tabulateImages/");
        registry.addResourceHandler("/bg/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/bg/");
        registry.addResourceHandler("/shareImages/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/shareImages/");
        registry.addResourceHandler("/chatImage/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/chatImage/");
    }
};
