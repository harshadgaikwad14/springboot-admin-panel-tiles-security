package com.example.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {

	Logger logger = LoggerFactory.getLogger(MyErrorController.class);

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		logger.info("request.getRequestURI() : {}", request.getRequestURI());
		//
//				Enumeration<String> headerNames = request.getHeaderNames();
//				while (headerNames.hasMoreElements()) {
//					String headerName = headerNames.nextElement();
//					System.out.println("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
//				}
		//
//				Enumeration<String> params = request.getParameterNames();
//				while (params.hasMoreElements()) {
//					String paramName = params.nextElement();
//					System.out.println("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
//				}
		//
//				Enumeration<String> requestAttributes = request.getAttributeNames();
//				while (requestAttributes.hasMoreElements()) {
//					String attributeName = (String) requestAttributes.nextElement();
//					System.out.println("Request Attribute Name: " + attributeName + ", Value - "
//							+ (request.getAttribute(attributeName)).toString());
//				}

		Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
		if (exception != null) {

			logger.info("exception : {}", exception.getMessage());
		}

		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		logger.info("requestUri : {}", requestUri);

		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		logger.info("status : {}", status);
		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				return "400ErrorPage";
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "500ErrorPage";
			}
		}
		return "genericErrorPage";
	}

	@Override
	public String getErrorPath() {
		return null;
	}
}