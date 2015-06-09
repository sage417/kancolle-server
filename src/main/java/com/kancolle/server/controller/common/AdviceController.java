/**
 * 
 */
package com.kancolle.server.controller.common;

import static com.kancolle.server.web.interceptor.APITokenHandlerInterceptor.MEMBER_ID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;

import com.kancolle.server.model.response.APIResponse;

/**
 * @author J.K.SAGE
 * @Date 2015年6月9日
 *
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class AdviceController {

    @ModelAttribute(MEMBER_ID)
    public String getMemberId(HttpServletRequest request) {
        return (String) request.getAttribute(MEMBER_ID);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody APIResponse<Object> processRuntimeException(NativeWebRequest request, RuntimeException e) {
        APIResponse<Object> api_response = new APIResponse<Object>();
        api_response.setApi_result(100);
        api_response.setApi_result_msg(e.getMessage());
        return api_response;
    }
}
