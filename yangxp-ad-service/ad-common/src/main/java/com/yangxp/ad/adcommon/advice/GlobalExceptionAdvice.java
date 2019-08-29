package com.yangxp.ad.adcommon.advice;

import com.yangxp.ad.adcommon.exception.AdException;
import com.yangxp.ad.adcommon.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: GlobalExceptionAdvice
 * @Description: TODO
 * @Auther: yangxp
 * @Date: 2019/8/29 20:41
 * @Version 1.0
 */

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handleAdException(HttpServletRequest request, AdException adException){

        CommonResponse<String> response = new CommonResponse<>(-1,"business");
        response.setData(adException.getMessage());
        return response;
    }
}
