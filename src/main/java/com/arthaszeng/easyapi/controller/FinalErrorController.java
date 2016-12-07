package com.arthaszeng.easyapi.controller;

import com.arthaszeng.easyapi.exception.ErrorInfo;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class FinalErrorController implements ErrorController {

    @RequestMapping(value = "/error")
    public ErrorInfo error(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        ErrorInfo errorInfo = new ErrorInfo();

        errorInfo.setCode(resp.getStatus());
        errorInfo.setData("DATA PLACEHOLDER");
        errorInfo.setMessage("MESSAGE PLACEHOLDER");
        errorInfo.setUrl(req.getRequestURL().toString());

        return errorInfo;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
