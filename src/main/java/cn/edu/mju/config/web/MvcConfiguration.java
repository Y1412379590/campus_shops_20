package cn.edu.mju.config.web;

import cn.edu.mju.web.filter.CodingFilter;
import cn.edu.mju.web.interceptor.ShopLoginInterceptor;
import cn.edu.mju.web.interceptor.ShopPermissionInterceptor;
import cn.edu.mju.web.listener.PathListener;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;


@Configuration
//将mvc交给我们自己管理
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer,ApplicationContextAware {


    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }



    /**
    * 定义默认的请求处理器
    */

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

        configurer.enable();
    }


    /**
     * 静态资源配置
     * 将/upload 映射为 D:/projectdev/image/upload
     * @param registry
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:D:\\projectdev\\image\\upload\\");
    }


    /*
    * 创建viewResolver
    * */

    @Bean(name = "viewResolver")
    public ViewResolver viewResolver(){

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setApplicationContext(applicationContext);

        viewResolver.setCache(false);

        viewResolver.setPrefix("/WEB-INF/html");

        viewResolver.setSuffix(".html");

        return viewResolver;

    }




    /*
    * 文件上传解析器
    * */
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver(){

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();

        multipartResolver.setDefaultEncoding("utf-8");

        multipartResolver.setMaxInMemorySize(20971520);

        multipartResolver.setMaxUploadSize(20971520);

        return multipartResolver;
    }


    //注册servlet 验证码
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean servlet = new ServletRegistrationBean(new KaptchaServlet(),"/kaptcha");
        servlet.addInitParameter("kaptcha.border","no");
        servlet.addInitParameter("kaptcha.textproducer.font.color","red");
        servlet.addInitParameter("kaptcha.image.width","135");
        servlet.addInitParameter("kaptcha.textproducer.char.string","ACDEFHKGPRSTWX23456789");
        servlet.addInitParameter("kaptcha.image.height","50");
        servlet.addInitParameter("kaptcha.textproducer.font.size","43");
        servlet.addInitParameter("kaptcha.noise.color","black");
        servlet.addInitParameter("kaptcha.textproducer.char.length","4");
        servlet.addInitParameter("kaptcha.textproducer.font.names","Arial");
        return servlet;
    }


    @Bean
    public FilterRegistrationBean filterRegistrationBean(){

        CodingFilter codingFilter = new CodingFilter();

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(codingFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;

    }


    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        PathListener pathListener = new PathListener();
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean(pathListener);
        return servletListenerRegistrationBean;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new ShopLoginInterceptor())
                .addPathPatterns("/shop/**");

        List<String> excludes = new ArrayList<>();
        excludes.add("/shop/shoplist");
        excludes.add("/shop/getshoplist");
        excludes.add("/shop/getshopinitinfo");
        excludes.add("/shop/shopoperation");
        excludes.add("/shop/shopmanage");
        excludes.add("/shop/getshopmanagementinfo");
        registry.addInterceptor(new ShopPermissionInterceptor())
                .addPathPatterns("/shop/**")
                .excludePathPatterns(excludes);


    }



}
