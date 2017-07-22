package com.arthaszeng.easyapi.service.movieData;

import com.arthaszeng.easyapi.model.User;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultUserService implements UserService {
    @Override
    public List<User> getAllUserList() throws AVException {
        AVQuery<AVObject> avQuery = new AVQuery<>("_User");
        List<AVObject> object = avQuery.find();

        return generateUserPayload(object);
    }

    @Override
    public List<User> getLimitedUserList(Integer limit) throws AVException {
        AVQuery<AVObject> avQuery = new AVQuery<>("_USER");
        avQuery.limit(limit);
        List<AVObject> object = avQuery.find();

        return generateUserPayload(object);
    }

    private List<User> generateUserPayload(List<AVObject> object) {
        List<User> resultList = new ArrayList<>();

        for (AVObject item : object) {
            String objectId = item.getObjectId();
            String email = item.getString("email");
            List images = item.getList("images");
            String city = item.getString("city");
            String nickName = item.getString("nickName");
            String avatar = item.getString("avatar");
            Integer gender = item.getInt("gender");
            String province = item.getString("province");
            String country = item.getString("country");

            User user = new User(objectId, email, images, city, nickName, avatar, gender, province, country);
            resultList.add(user);
        }
        return resultList;
    }
}
