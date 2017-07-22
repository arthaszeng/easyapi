package com.arthaszeng.easyapi.controller;

import com.arthaszeng.easyapi.model.User;
import com.arthaszeng.easyapi.service.movieData.UserService;
import com.avos.avoscloud.AVException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(notes = "User Interface", value = "Users", httpMethod = "GET")
    private ResponseEntity<List> queryUsers(@RequestParam @ApiParam Integer limit) throws AVException, IOException {

        List<User> resultList = userService.getLimitedUserList(limit);

        return new ResponseEntity<>(resultList, OK);
    }
}
