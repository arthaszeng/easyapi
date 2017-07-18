package com.arthaszeng.easyapi.service.movieData;

import com.arthaszeng.easyapi.model.MovieData;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DefaultMovieDataService implements MovieDataService {
    @Override
    public List<MovieData> getAllMovieDataList() throws AVException {
        AVQuery<AVObject> avQuery = new AVQuery<>("MovieData");
        List<AVObject> object = avQuery.find();

        return generateMovieData(object);
    }

    @Override
    public List<MovieData> getLimitedMovieDataList(Integer limit) throws AVException {
        AVQuery<AVObject> avQuery = new AVQuery<>("MovieData");
        avQuery.limit(limit);
        List<AVObject> object = avQuery.find();

        return generateMovieData(object);
    }

    private List<MovieData> generateMovieData(List<AVObject> object) {
        List<MovieData> resultList = new ArrayList<>();

        for (AVObject item : object) {
            String score = item.getString("score");
            String picUrl = item.getString("picUrl");
            String name = item.getString("name");
            boolean isRender = item.getBoolean("isRender");
            int zIndex = item.getInt("zIndex");

            String objectId = item.getObjectId();
            Date createdAt = item.getCreatedAt();
            Date updatedAt = item.getUpdatedAt();

            MovieData movieData = new MovieData(score, picUrl, createdAt, name, isRender, objectId, zIndex, updatedAt);
            resultList.add(movieData);
        }
        return resultList;
    }
}
