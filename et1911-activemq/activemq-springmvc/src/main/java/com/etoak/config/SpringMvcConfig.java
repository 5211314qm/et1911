package com.etoak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;


/**
 * 这是spring mvc容器
 * 1、component-scan
 * 2、mvc:annotation-driven
 * 3、mvc:default-servlet-handler
 * 4、mvc:resources(如果用到可以配置)
 * 5、Thymeleaf的三个bean
 * 6、拦截器
 * 7、mvc:view-controller
 * @author 王贺一
 *
 */
@Configuration
@ComponentScan(basePackages = 
	{"com.etoak"},
	includeFilters = {@Filter(type=FilterType.ANNOTATION,
	classes= {Controller.class,RestController.class,ControllerAdvice.class,EnableWebMvc.class})},
	excludeFilters = {@Filter(type = FilterType.ANNOTATION,
	classes= {Service.class,Repository.class})}
)
@EnableWebMvc //<mvc:annotation-driven>
public class SpringMvcConfig implements WebMvcConfigurer{

	/**
	 * <mvc:default-servlet-handler />
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	
	/**
	 * <mvc:resources location="" mapping="" />
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/pic/**")
			.addResourceLocations("file:f:/foto/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}
	
	/**
	 * Thymeleaf
	 * SpringResourceTemplateResolver
	 * SpringTemplateEngine
	 * ThymeleafViewResolver
	 */
	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		//属性 prefix、suffix、characterEncoding、templateMode、cacheable
		resolver.setPrefix("classpath:/templates/");
		resolver.setSuffix(".html");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setCacheable(false);
		
		return resolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(this.templateResolver());
		return templateEngine;
	}
	
	@Bean
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(this.templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");

		return viewResolver;
	}

}
