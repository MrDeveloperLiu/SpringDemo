package com.ly.SpringDemo.Controller;

import com.ly.SpringDemo.Common.Response.SResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorController extends BasicErrorController {

    @Value("${server.error.path:${error.path:/error}}")
    private String path;

    public ErrorController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        Map<String, Object> originalMsgMap = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        String path = (String)originalMsgMap.get("path");
        String error = (String)originalMsgMap.get("error");
        String message = (String)originalMsgMap.get("message");
        SResponse response = new SResponse(status.value(), String.format("%s:%s %s", path, message, error), null);
        return new ResponseEntity<Map<String, Object>>(response.toMap(), status);
    }

//    @Override
//    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
//        //请求的状态
//        HttpStatus status = getStatus(request);
//        response.setStatus(getStatus(request).value());
//        Map<String, Object> model = getErrorAttributes(request,
//                isIncludeStackTrace(request, MediaType.TEXT_HTML));
//        ModelAndView modelAndView = resolveErrorView(request, response, status, model);
//        return (modelAndView == null ? new ModelAndView("error", model) : modelAndView);
//    }
}
