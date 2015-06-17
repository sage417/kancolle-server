package com.kancolle.server.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kancolle.server.service.member.MemberService;

public class APITokenHandlerInterceptor extends HandlerInterceptorAdapter {
    private static final String API_TOKEN = "api_token";
    private static final String API_VERNO = "api_verno";
    private static final String API_NOW_VERSION = "1";

    private static class VersionExceptionHolder {
        private static final RuntimeException INSTANCE = new RuntimeException("version error");
    }

    private static class TokenExceptionHolder {
        private static final RuntimeException INSTANCE = new RuntimeException("token error");
    }

    public static final String MEMBER_ID = "member_id";

    @Autowired
    private MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String version = StringUtils.defaultString(request.getParameter(API_VERNO), API_NOW_VERSION);
        if (!API_NOW_VERSION.equals(version))
            throw VersionExceptionHolder.INSTANCE;

        String api_token = request.getParameter(API_TOKEN);
        if (StringUtils.isEmpty(api_token))
            throw TokenExceptionHolder.INSTANCE;

        try {
            String member_id = memberService.getMemberByApiToken(api_token);
            request.setAttribute(MEMBER_ID, member_id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            throw TokenExceptionHolder.INSTANCE;
        }
    }
}
