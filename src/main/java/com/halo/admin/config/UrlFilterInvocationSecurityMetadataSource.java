package com.halo.admin.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;

/**
 * @author Hailuo
 * @Date: 2019/3/7 11:05
 * @Description： 一个请求到达服务器后先经过此处
 */
@Slf4j
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private AntPathMatcher antPathMatcher = new AntPathMatcher();


	/**
	 * 获取与请求路径一致的资源信息，如果一致则封装ConfigAttribute返回
	 * @param o
	 * @return 如果返回null或者空集合则直接请求路径对应的资源
	 * @throws IllegalArgumentException
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) o).getRequestUrl();
		log.info("request:{}",requestUrl);
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return false;
	}
}
