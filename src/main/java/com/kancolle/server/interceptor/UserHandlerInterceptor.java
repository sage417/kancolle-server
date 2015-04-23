package com.kancolle.server.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kancolle.server.service.IUserService;

public class UserHandlerInterceptor extends HandlerInterceptorAdapter {
    private static final String API_TOKEN = "api_token";
    private static final String USER_ID = "user_id";
    private static final String ERROR_FORWARD_URL = "/common/badrequest";

    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String api_token = (String) request.getAttribute(API_TOKEN);

        String user_id = userService.getUserIdByApiToken(api_token);

        if (user_id != null) {
            request.setAttribute(USER_ID, user_id);
            return true;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_FORWARD_URL);
        dispatcher.forward(request, response);
        return false;
    }
}
