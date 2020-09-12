package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/*@SpringBootApplication
public class SpringbootTilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTilesApplication.class, args);
	}

}	
*/
@SpringBootApplication
public class SpringbootTilesApplication extends SpringBootServletInitializer{
	
	
	
	 private static Class<SpringbootTilesApplication> applicationClass = SpringbootTilesApplication.class;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTilesApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

}