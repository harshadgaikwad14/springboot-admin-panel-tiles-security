package com.example.configuration;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
 
@Configuration
public class TilesConfiguration {
     
     
    @Bean(name = "viewResolver")
    public ViewResolver getViewResolver() {
        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
 
        // TilesView 3
        viewResolver.setViewClass(TilesView.class);
 
        return viewResolver;
    }
 
	/*
	 * @Bean(name = "tilesConfigurer") public TilesConfigurer getTilesConfigurer() {
	 * TilesConfigurer tilesConfigurer = new TilesConfigurer();
	 * 
	 * // TilesView 3 tilesConfigurer.setDefinitions("WEB-INF/tiles.xml");
	 * 
	 * return tilesConfigurer; }
	 */
    
    
    @Bean(name = "tilesConfigurer") 
    public TilesConfigurer getTilesConfigurer() {
    	TilesConfigurer configurer = new TilesConfigurer();
       // configurer.setDefinitions("/WEB-INF/tiles.xml");
        configurer.setDefinitions(new String[] { "/WEB-INF/tiles.xml" });
    	//configurer.setDefinitions("classpath:tiles.xml");
        configurer.setCheckRefresh(true);
        return configurer;
    }
     
     
 
}