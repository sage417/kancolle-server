package com.kancolle.server.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kancolle.server.service.member.MemberService;

public class APITokenHandlerInterceptor extends HandlerInterceptorAdapter {
    private static final String API_TOKEN = "api_token";
    private static final String API_VERNO = "api_verno";
    private static final String TOKEN_ERROR_URL = "/common/tokenerror";

    public static final String MEMBER_ID = "member_id";

    @Autowired
    private MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String api_token = request.getParameter(API_TOKEN);
        try {
            String member_id = memberService.getMemberByApiToken(api_token);
            request.setAttribute(MEMBER_ID, member_id);

            //int api_verno = Integer.valueOf(request.getParameter(API_VERNO));

            return true;
        } catch (EmptyResultDataAccessException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(TOKEN_ERROR_URL);
            dispatcher.forward(request, response);
        }
        return false;
    }
}
