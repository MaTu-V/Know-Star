package com.wd.zykt.config;

import com.wd.zykt.controller.BasicController;
import com.wd.zykt.interceptor.MyInterceptor;
import com.wd.zykt.utils.ReadProteriesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig extends BasicController implements WebMvcConfigurer {

    @Autowired
    private ReadProteriesUtil readProteriesUtil;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("file:"+ readProteriesUtil.FILE_SPACE + "/");
    }

    @Bean
    public MyInterceptor createInterceptor(){
        return new MyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //加入拦截器
        //小程序进入后拦截部分关于用户请求
        registry.addInterceptor(createInterceptor()).addPathPatterns("/user/**","/collection/**");
    }
}
