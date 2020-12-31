package org.zerock.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

@Slf4j
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    //프로젝트에서 사용한 bean들을 정의하기 위한 클래스를 지정 root-context지정
    @Override
    protected Class<?>[] getRootConfigClasses() {
        log.info("==================getRootConfigClasses=================");
        return new Class[]{RootConfig.class};
    }

    //spring mvc프로젝트 설정을 위한 클래스를 지정한다,servlet-context지정
    @Override
    protected Class<?>[] getServletConfigClasses() {
        log.info("==================getServletConfigClasses=================");
        return new Class[]{ServletConfig.class};
    }

    //모든 요청에 대해서 dispathcerservlet이 반응을 하겠다.
    @Override
    protected String[] getServletMappings() {
        log.info("==================getServletMappings=================");
        return new String[]{"/"};
    }


    // 404에러페이지 다르게 처리하기 위한 설정

    // 방법1
//    @Override
//    protected DispatcherServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
//        final DispatcherServlet dispatcherServlet = (DispatcherServlet) super.createDispatcherServlet(servletAppContext);
//        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
//        return dispatcherServlet;
//    }

    //방법2
    // throwExceptionIfNoHandlerFound를 true로 설정하여 핸들러가 발견되지 않을 때 예외가 발생
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }

    //파라미터 인코딩 필터 설정
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return new Filter[]{characterEncodingFilter};
    }
}
