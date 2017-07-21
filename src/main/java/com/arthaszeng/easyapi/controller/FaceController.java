package com.arthaszeng.easyapi.controller;

import com.arthaszeng.easyapi.model.Age;
import com.arthaszeng.easyapi.model.Face;
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

    private static final String FEATURE_HOST = "http://faceall.market.alicloudapi.com";
    private static final String FEATURE_PATH = "/v2/detection/detect";
    private static final String APPCODE = "a827b76cafa7433b9eaa6ec30e837d91";
    private static final String AGE_PATH = "/rest/160601/face/age_detection.json";
    private static final String AGE_HOST = "https://dm-23.data.aliyun.com";
    private static final String METHOD_POST = "POST";
    private static final Map<String, String> QUERYS = new HashMap<>();

    @RequestMapping(value = "/age", method = RequestMethod.GET)
    @ApiOperation(notes = "Identify Age", value = "Age", httpMethod = "GET")
    private ResponseEntity<Age> identifyAge(@RequestParam @ApiParam String url) throws Exception {
        return new ResponseEntity<>(new Age(queryAge(url)), OK);
    }

    @RequestMapping(value = "/feature", method = RequestMethod.GET)
    @ApiOperation(notes = "Identify Face Feature", value = "Feature", httpMethod = "GET")
    private ResponseEntity<Face> identifyFaceFeature(@RequestParam @ApiParam String url) throws Exception {
        return new ResponseEntity<>(queryFaceFeature(url), OK);
    }

    private static Face queryFaceFeature(String url) throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + APPCODE);
        headers.put("Content-Type", "application/json; charset=UTF-8");
        headers.put("Content-Type", "application/json");
        String bodys = format("{\"img_url\":\"%s\",\"attributes\":\"true\"}", url);

        try {
            HttpResponse response = HttpUtils.doPost(FEATURE_HOST, FEATURE_PATH, METHOD_POST, headers, QUERYS, bodys);
            return ParserUtil.parseFeature(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new Face("", "");
        }
    }

    private String queryAge(String url) throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + APPCODE);
        headers.put("Content-Type", "application/json; charset=UTF-8");
        String bodys = format("{\"inputs\":[{\"image\":{\"dataType\":50,\"dataValue\":\"%s\"}}]}", ImageUtil.encodeImage(url));

        try {
            HttpResponse response = HttpUtils.doPost(AGE_HOST, AGE_PATH, METHOD_POST, headers, QUERYS, bodys);
            return ParserUtil.parseAge(response);
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }
}
