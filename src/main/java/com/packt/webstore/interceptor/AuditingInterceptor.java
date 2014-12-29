package com.packt.webstore.interceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuditingInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = Logger.getLogger("auditLogger");
	private String user;
	private String productId;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (request.getRequestURI().endsWith("/products/add") && request.getMethod().equals("POST")) {
			user = request.getRemoteUser();
			productId = request.getParameterValues("productId")[0];
		}

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (request.getRequestURI().endsWith("/products/add") && response.getStatus() == 302) {
			logger.info(String.format("A new product [%s] , is added by [%s] on %s", productId, user, getCurrentTime()));
		}
	}

	private String getCurrentTime() {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return formatter.format(calendar.getTime());
	}
}
