package com.mw.education.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        // 在LoginController中定义了以下特定的URL的handler method
        /*
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns("/login", "/logout", "/login-info")
                .addPathPatterns("/**");

         */
    }

    public static class LoginInterceptor implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            // 在LoginController中，登录成功则设置以下session attribute
            if(request.getSession().getAttribute("user-code") != null
                    && request.getSession().getAttribute("user-type") != null) {

                // todo: 可以在这里按角色来限定访问哪些URL。
                // todo: 实际中更多地是依赖数据库进行细粒度的定义，及使用注解来判别

                // session中已登记。放行
                return true;
            }
            else {
                // 未登录或者session过期等，返回与AjaxResult一致格式的Json
                response.setContentType("application/json");
                response.getWriter().print(
                        "{"
                            + "\"code\" : 401, "
                            + "\"msg\" :  \"not login\" "
                        + "}"
                );
                return false;
            }
        }
    }
}
