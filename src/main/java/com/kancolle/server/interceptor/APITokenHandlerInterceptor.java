package com.kancolle.server.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kancolle.server.mapper.player.Player;
import com.kancolle.server.service.player.PlayerService;

public class APITokenHandlerInterceptor extends HandlerInterceptorAdapter {
    private static final String API_TOKEN = "api_token";
    private static final String TOKEN_ERROR_URL = "/common/tokenerror";

    public static final String PLAYER = "player";

    @Autowired
    private PlayerService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String api_token = (String) request.getParameter(API_TOKEN);
        try {
            Player player = userService.getPlayerByApiToken(api_token);
            request.setAttribute(PLAYER, player);
            return true;
        } catch (EmptyResultDataAccessException e) {
            // RequestDispatcher dispatcher =
            // request.getRequestDispatcher(TOKEN_ERROR_URL);
            // dispatcher.forward(request, response);
        }
        return true;
    }
}
