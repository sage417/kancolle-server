/**
 *
 */
package com.kancolle.server.controller.common;

import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.member.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author J.K.SAGE
 * @Date 2015年6月9日
 */
//@ControllerAdvice(value = "com.kancolle.server.controller.kcsapi")
@RestControllerAdvice(value = "com.kancolle.server.controller.kcsapi")
public class AdviceController {
    private static final Logger logger = LoggerFactory.getLogger(AdviceController.class);

    private static final String API_NOW_VERSION = "1";
    public static final String MEMBER_ID = "member_id";

    @Autowired
    private MemberService memberService;

    @ModelAttribute(MEMBER_ID)
    public String checkParams(
            @RequestParam(value = "api_verno", defaultValue = API_NOW_VERSION) String version,
            @RequestParam(value = "api_token") String token) {
        if (!StringUtils.equals(version, API_NOW_VERSION))
            throw new RuntimeException("version invalid");
        try {
            return memberService.getMemberByApiToken(token);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("token invalid");
        }
    }

    //@ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public APIResponse<Object> processRuntimeException(NativeWebRequest nativeWebRequest, RuntimeException e) {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        logger.error("ip:{} url:{} reason:{}", request.getRemoteAddr(), request.getRequestURI(), e.getMessage(), e);
        return APIResponse.EMPTY_FAILED_RESPONSE;
    }
}
