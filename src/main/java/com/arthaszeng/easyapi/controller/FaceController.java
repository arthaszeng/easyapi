package com.arthaszeng.easyapi.controller;

import com.arthaszeng.easyapi.model.Age;
import com.arthaszeng.easyapi.utils.HttpUtils;
import com.arthaszeng.easyapi.utils.ImageUtil;
import com.arthaszeng.easyapi.utils.ParserUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.http.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/face")
public class FaceController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(notes = "Identify Age", value = "Age", httpMethod = "GET")
    private ResponseEntity<Age> identifyAge(@RequestParam @ApiParam String url) throws Exception {
        return new ResponseEntity<>(new Age(queryAge(url)), OK);
    }

    private String queryAge(String url) throws Exception {
        String host = "https://dm-23.data.aliyun.com";
        String path = "/rest/160601/face/age_detection.json";
        String method = "POST";
        String appcode = "a827b76cafa7433b9eaa6ec30e837d91";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + appcode);
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<>();
        String bodys = format("{\"inputs\":[{\"image\":{\"dataType\":50,\"dataValue\":\"%s\"}}]}", ImageUtil.encodeImage(url));

        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            return ParserUtil.parseAge(response);
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }

}
