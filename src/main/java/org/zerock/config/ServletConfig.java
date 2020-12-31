package org.zerock.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//Controller 어노테이션이 셋팅되어 있는 클래스를 Controller로 등록한다.
@EnableWebMvc
//스캔할 패키지를 지정한다.
@ComponentScan(basePackages = {"org.zerock.controller", "org.zerock.exception"})
public class ServletConfig implements WebMvcConfigurer {

    /*
     * configureDefaultServletHandling(): 디폴트 서블릿 핸들러를 위한 설정이며,
     * DispacherServlet의 매핑 경로를 /로 줄 때 JSP/HTML/CSS 등 올바르게 처리를 위하여 configureDefaultServletHandling을 설정합니다.
     */

    /*
     * throwExceptionIfNoHandlerFound를 true로 설정해도 핸들러가 발견되지 않을 때 예외(custom 404 error)가 발생하지 않는 경우
     * DispatcherServlet.setThrowExceptionIfNoHandlerFound에서 DefaultServletHttpRequestHandler가 사용되는 경우
     * NoHandlerFoundException이 발생하지 않으므로 주석 처리함.
     */
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }

    /*Controller의 메서드가 반환하는 jsp의 이름 앞뒤에 경로와 확장자를 붙혀주도록 설정한다.*/
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        registry.viewResolver(bean);
    }

    /*정적 파일의 경로를 매핑한다.*/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    /*file*/
//    @Bean(name = "multipartResolver")
//    public CommonsMultipartResolver getResolver() throws IOException {
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//
//        // 10MB
//        resolver.setMaxUploadSize(1024 * 1024 * 10);
//
//        // 2MB
//        resolver.setMaxUploadSizePerFile(1024 * 1024 * 2);
//
//        // 1MB
//        resolver.setMaxInMemorySize(1024 * 1024);
//
//        // temp upload
//        resolver.setUploadTempDir(new FileSystemResource("C:\\upload\\tmp"));
//
//        resolver.setDefaultEncoding("UTF-8");
//
//        return resolver;
//    }

    /*인터셉터*/
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//    }
}
