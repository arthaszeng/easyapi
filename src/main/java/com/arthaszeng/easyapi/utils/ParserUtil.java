package com.arthaszeng.easyapi.utils;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ParserUtil {
    public static String parseAge(HttpResponse response) throws IOException {
        String entity = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(entity);
        JSONArray outputs = jsonObject.getJSONArray("outputs");
        JSONObject object = (JSONObject) outputs.get(0);
        JSONObject outputValue = object.getJSONObject("outputValue");
        JSONObject dataValue = new JSONObject((String) outputValue.get("dataValue"));
        JSONArray ages = (JSONArray) dataValue.get("age");
        return ages.get(0).toString();
    }
}
