package com.halo.admin.config;

import com.halo.admin.service.UserService;
import com.halo.admin.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.cors.CorsUtils;

import java.io.Writer;

/**
 * @author Hailuo
 * @Date: 2019/4/3 15:53
 * @Description：
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    @Autowired
    private UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
    @Autowired
    private UrlAccessDecisionManager urlAccessDecisionManager;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //验证密码   密码=原始密码+SALT 取md5
        auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder() {
            static final String SALT = "pass";

            @Override
            public String encode(CharSequence rawPassword) {
                return Md5Util.encode(rawPassword.toString() + SALT);
            }

            /**
             * 比对密码
             * @param rawPassword 登录时传入的密码
             * @param encodedPassword 数据库中的密码
             * @return 根据匹配规则返回true or false
             */
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encode(rawPassword).equals(encodedPassword);
            }
        });
    }

    /**
     * 配置需要无需拦截的资源
     *
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/admin/login", "/static/**", "/favicon.ico", "/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/admin/logina","/index").permitAll()
                .antMatchers("/api/**").authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                })
                .and().headers().frameOptions().sameOrigin()
                //请求跳转登录页面
                .and().formLogin().loginPage("/admin/login")
                .loginProcessingUrl("/admin/logina").usernameParameter("userName").passwordParameter("passWord")//处理登录逻辑的路径
                .defaultSuccessUrl("/index").failureUrl("/admin/login?error").permitAll()
                .and().logout().permitAll()
                .and().exceptionHandling().accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> {
                    e.printStackTrace();
                    Writer writer = httpServletResponse.getWriter();
                    writer.write("access denied");
                    writer.flush();
                    writer.close();
                    log.error("access denied");
                }).and().csrf().disable();
    }
}
