package com.example.configuration;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class MultipartConfig {

	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(20848820);
		return multipartResolver;
	}
}
