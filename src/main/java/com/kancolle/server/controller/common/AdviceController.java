/**
 * 
 */
package com.kancolle.server.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;

import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.member.MemberService;

/**
 * @author J.K.SAGE
 * @Date 2015年6月9日
 *
 */
@ControllerAdvice
public class AdviceController {
    private static final Logger logger = LoggerFactory.getLogger(AdviceController.class);
    public static final APIResponse<Object> DEFAULT_RESPONSE = new APIResponse<>();

    private static final String API_TOKEN = "api_token";
    private static final String API_VERNO = "api_verno";
    private static final String API_NOW_VERSION = "1";
    public static final String MEMBER_ID = "member_id";

    @Autowired
    private MemberService memberService;

    @ModelAttribute(MEMBER_ID)
    public String getMemberId(HttpServletRequest request) {
        String version = StringUtils.defaultString(request.getParameter(API_VERNO), API_NOW_VERSION);
        if (!API_NOW_VERSION.equals(version))
            throw new RuntimeException("version error");

        String api_token = request.getParameter(API_TOKEN);
        if (StringUtils.isEmpty(api_token))
            throw new RuntimeException("token error");

        try {
            String member_id = memberService.getMemberByApiToken(api_token);
            request.setAttribute(MEMBER_ID, member_id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("token error");
        }
        return (String) request.getAttribute(MEMBER_ID);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody APIResponse<Object> processRuntimeException(NativeWebRequest request, RuntimeException e) {
        logger.warn(e.getMessage(), e);
        APIResponse<Object> api_response = new APIResponse<Object>();
        api_response.setApi_result(100);
        api_response.setApi_result_msg("失败");
        return api_response;
    }
}
