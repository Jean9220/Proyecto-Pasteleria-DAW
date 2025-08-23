package com.example.Proyecto_DAW.admin.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/ecommerce/**") // Protege las rutas de ecommerce
                .excludePathPatterns("/login", "/registro", "/css/**", "/js/**", "/images/**"); // Excluye login y recursos estáticos
    }

    private static class AuthInterceptor implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            HttpSession session = request.getSession(false);
            String uri = request.getRequestURI();
            if (session == null || session.getAttribute("clienteId") == null) {
                response.sendRedirect("/login");
                return false;
            }
            return true;
        }
    }
}