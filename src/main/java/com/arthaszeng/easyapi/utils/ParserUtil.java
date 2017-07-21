package com.arthaszeng.easyapi.utils;

import com.arthaszeng.easyapi.model.Face;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigInteger;

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

    public static Face parseFeature(HttpResponse response) throws IOException {
        String entity = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(entity);
        JSONArray faces = jsonObject.getJSONArray("faces");
        JSONObject face = (JSONObject) faces.get(0);
        JSONObject attributes = face.getJSONObject("attributes");
        BigInteger beauty = attributes.getBigInteger("beauty");
        BigInteger age = attributes.getBigInteger("age");

        return new Face(beauty.toString(), age.toString());
    }
}
