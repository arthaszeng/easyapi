package com.arthaszeng.easyapi.controller;

import com.arthaszeng.easyapi.model.MovieData;
import com.arthaszeng.easyapi.service.movieData.MovieDataService;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
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

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieDataService movieDataService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(notes = "spike for Leancloud", value = "Movies", httpMethod = "GET")
    private ResponseEntity<List> queryMovies(@RequestParam @ApiParam Integer limit) throws AVException, IOException {

        List<MovieData> resultList = movieDataService.getLimitedMovieDataList(limit);

        return new ResponseEntity<>(resultList, OK);
    }

    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    @ApiOperation(notes = "spike for Leancloud", value = "Movies", httpMethod = "POST")
    private ResponseEntity<AVObject> createMovie(@RequestParam @ApiParam String objectId) throws AVException {
        AVObject movie = null;
        try {
            movie = new AVObject("MovieData");
            movie.put("isRender", true);
            movie.put("name", "纵横四海");
            movie.put("picUrl", "https://qnmob2.doubanio.com/view/movie_poster_cover/lpst/public/p933122297.jpg?imageView2/0/q/80/w/9999/h/800/format/jpg");
            movie.put("score", "9.0");
            movie.put("zIndex", 9999);
            movie.save();
        } catch (AVException e) {
            e.printStackTrace();
            return new ResponseEntity<>(movie, INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(movie, OK);
    }
}
