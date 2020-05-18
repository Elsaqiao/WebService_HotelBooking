package com.miage.bookingHotel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }
    //静态资源释放
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginHandlerIntercepter()).
//                addPathPatterns("/**").
//                excludePathPatterns("/index.html","/","/login.html","/admin","/admin/**","/bootstrap/**","/css/**","/font-awesome.css/**","/images/**","/img/**","/js/**","/favicon1.ico");
//    }
}
