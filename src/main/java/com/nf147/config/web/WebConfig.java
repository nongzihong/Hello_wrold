package com.nf147.config.web;

import com.nf147.config.mvc.SpringWebConfig;
import com.nf147.config.spring.RootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.swing.*;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() { // spring(容器) 父容器
        return new Class[] {RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {//(spring 容器) 子容器
        return new Class[] {SpringWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() { //映射
        return new String[]{"/"};
    }


}
