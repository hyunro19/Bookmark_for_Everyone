package com.hyunro.bookmark.config.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hyunro.bookmark.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor{
    private static final String HEADER_AUTH = "authorization";

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        final String token = request.getHeader(HEADER_AUTH);
        if (request.getMethod().equals("OPTIONS")) {
//            System.out.println("OPTIONS");
            return true;
        }

//        System.out.println("prehandle, "+token);

//        if(token != null && jwtService.isUsable(token)){
//            return true;
//        }else{
//            throw new UnauthorizedException();
//        }
        return true;

    }
}
