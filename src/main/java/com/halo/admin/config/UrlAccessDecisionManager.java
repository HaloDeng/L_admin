package com.halo.admin.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @Auther: halo
 * @Date: 2019/6/2 14:32
 * @Description:
 */
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {

    /**
     * @param authentication   当前的用户
     * @param object
     * @param configAttributes 当前url所需要的权限，{UrlFilterInvocationSecurityMetadataSource.getAttributes}处理后的数据
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute c : configAttributes) {
            String needRole = c.getAttribute();
            if ("ROLE_LOGIN".equals(needRole)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    //AnonymousAuthenticationToken表示当前用户未登录
                    throw new BadCredentialsException("当前页面需要登录访问");
                }
                //未分配角色的路径直接放行
                return;
            }
            //当前用户所拥有的权限，也就是角色名
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            if (authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()).contains(needRole)) {
                return;
            }
        }
        throw new AccessDeniedException("权限不足");


    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
