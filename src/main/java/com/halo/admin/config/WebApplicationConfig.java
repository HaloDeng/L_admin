package com.halo.admin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WebApplicationConfig
 * @Description TODO
 * @Author haiLuo
 * @Date 2019/5/17 16:35
 * @Version 1.0
 */
@Slf4j
@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                String uri = request.getRequestURI();
                if (!uri.contains("static")) {
                    log.info("IP:"+request.getRemoteAddr()+"---uri:"+uri+"---parameters:"+paramStr(request));
                }
                return true;
            }

            private String paramStr(HttpServletRequest request) {
                Map<String, Object> params = new HashMap<>();
                Enumeration<String> paramsKey = request.getParameterNames();
                while (paramsKey.hasMoreElements()) {
                    String key = paramsKey.nextElement();
                    params.put(key, request.getParameter(key));
                }
                return params.toString();
            }
        });
    }
}
