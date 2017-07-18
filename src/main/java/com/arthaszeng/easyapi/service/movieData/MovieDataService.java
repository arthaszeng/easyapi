package com.arthaszeng.easyapi.service.movieData;

import com.arthaszeng.easyapi.model.MovieData;
import com.avos.avoscloud.AVException;

import java.util.List;

public interface MovieDataService {
    List<MovieData> getAllMovieDataList() throws AVException;
    List<MovieData> getLimitedMovieDataList(Integer limit) throws AVException;
}
