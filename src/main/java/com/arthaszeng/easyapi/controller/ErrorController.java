package com.arthaszeng.easyapi.controller;

import com.arthaszeng.easyapi.exception.DatabaseException;
import com.arthaszeng.easyapi.exception.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorController {

    @RequestMapping("/error")
    @ExceptionHandler(value = DatabaseException.class)
    public ErrorInfo error(HttpServletRequest request, DatabaseException e) throws Exception {

        ErrorInfo<String> errorInfo = new ErrorInfo<>();

        errorInfo.setMessage(e.getMessage());
        errorInfo.setCode(ErrorInfo.ERROR);
        errorInfo.setData("Some Data");
        errorInfo.setUrl(request.getRequestURL().toString());

        return errorInfo;
    }
}
