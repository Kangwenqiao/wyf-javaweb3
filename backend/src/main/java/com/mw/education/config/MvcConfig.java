package com.mw.education.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * MVC配置类，用于注册和配置Spring MVC的拦截器。
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 注册拦截器并配置其排除路径和适用路径。
     * @param registry 拦截器注册表，用于添加和配置拦截器。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO: 在此处添加更多拦截器配置
    }

    /**
     * 登录拦截器类，用于拦截请求以验证用户是否已登录。
     */
    public static class LoginInterceptor implements HandlerInterceptor {
        /**
         * 在请求处理之前执行的逻辑。
         * 主要用于检查用户是否已登录。
         * @param request HTTP请求对象。
         * @param response HTTP响应对象。
         * @param handler 处理器对象，表示将要处理的请求。
         * @return 如果用户已登录则返回true，否则返回false。
         * @throws Exception 如果处理过程中发生异常。
         */
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            // 检查用户是否已登录
            if(request.getSession().getAttribute("user-code") != null
                    && request.getSession().getAttribute("user-type") != null) {
                return true;
            } else {
                // 如果用户未登录，则返回登录请求
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
