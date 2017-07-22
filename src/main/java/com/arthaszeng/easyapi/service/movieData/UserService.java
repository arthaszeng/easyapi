package com.arthaszeng.easyapi.service.movieData;

import com.arthaszeng.easyapi.model.User;
import com.avos.avoscloud.AVException;

import java.util.List;

public interface UserService {
    List<User> getAllUserList() throws AVException;
    List<User> getLimitedUserList(Integer limit) throws AVException;
}
