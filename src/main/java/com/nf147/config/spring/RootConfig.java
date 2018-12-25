package com.nf147.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.nf147.dao")//扫描dao 包 使用spring 注解接口
@Import({SpringDaoConfig.class,SpringServlet.class})//导入其他配置
public class RootConfig {
}
