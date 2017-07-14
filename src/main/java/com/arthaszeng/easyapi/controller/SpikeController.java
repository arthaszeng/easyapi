package com.arthaszeng.easyapi.controller;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/spike")
public class SpikeController {

    @RequestMapping("/")
    @ApiOperation(notes = "spike for Leancloud", value = "Category ID", httpMethod = "GET")
    private ResponseEntity<String> haha() throws AVException {
        AVObject testObject = new AVObject("TestObject");
        testObject.put("words","Hello World!");
        testObject.save();
        return new ResponseEntity<>("Ok", OK);
    }


}
