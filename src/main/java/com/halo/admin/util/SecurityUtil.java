package com.halo.admin.util;

import com.halo.admin.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Auther: halo
 * @Date: 2019/6/19 21:11
 * @Description: spring security辅助类
 */
public class SecurityUtil {

    public static User currentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
