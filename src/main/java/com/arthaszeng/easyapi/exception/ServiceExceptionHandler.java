//package com.arthaszeng.easyapi.exception;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import javax.management.ServiceNotFoundException;
//import javax.servlet.http.HttpServletRequest;
//
//@EnableWebMvc
//@ControllerAdvice
//public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @Override
//    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        ErrorInfo errorInfo = new ErrorInfo();
//        errorInfo.setUrl(request.getContextPath());
//        errorInfo.setData("data");
//        errorInfo.setCode(status.value());
//        errorInfo.setMessage("message");
//
//        return new ResponseEntity<>(errorInfo, status);
//    }
//
//    @ExceptionHandler(Throwable.class)
//    @ResponseBody
//    ResponseEntity<Object> handleControllerException(HttpServletRequest req, Throwable ex) {
//        ErrorResponse errorResponse = new ErrorResponse(ex);
//        errorResponse.setDetails(ex.toString());
//
//        if (ex instanceof DatabaseException) {
//            return new ResponseEntity<Object>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        if (ex instanceof ServiceNotFoundException) {
//            return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_GATEWAY);
//        }
//    }
//}