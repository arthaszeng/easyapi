package com.arthaszeng.easyapi.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@RestController
@RequestMapping("/")
public class QRCoderController {
    private static final String FETCH_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxfd65148b63fb1c13&secret=3b10c329e0c5f2874f53eab5ccf0a792";
    private static final String URL_PREFIX = "http://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";
    private static final String FOLDER_PATH_PREFIX = "/Users/cwzeng/Documents/QRCode/";
    private String folderPath;
    private String token;

    @RequestMapping("/code")
    @ApiOperation(notes = "Create a batch of QR Type B Codes", value = "number", httpMethod = "GET")
    public String createQRCode(@RequestParam @ApiParam String number) throws Exception {
        createFolder();
        getToken();

        if (validate(number)) {
            for (int i = 0; i < Integer.valueOf(number); i++) {
                generateCodes();
            }
            return "Ok";
        } else {
            return "Wrong parameters: number";
        }
    }

    private byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    private void generateCodes() throws Exception {
        String codeId = generateCodeId();
        sendPost(getUrl(), codeId);
    }

    private String getUrl() throws Exception {
        return URL_PREFIX + token;
    }

    private void getToken() throws Exception {
        token = sendGet();
    }

    private String sendGet() throws Exception {
        URL obj = new URL(FETCH_TOKEN_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", USER_AGENT);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());
        return response.toString();
    }

    private String getDataJSON(String id) {
        return "{\n\"scene\":\"" + id + "\"\n}";
    }

    private void sendPost(String urlStr, String id) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");

        String dataJSON = getDataJSON(id);
        OutputStream os = conn.getOutputStream();
        os.write(dataJSON.getBytes("UTF-8"));
        os.close();

        InputStream inStream = conn.getInputStream();
        saveFile(id, inStream);
    }

    private void saveFile(String id, InputStream inStream) throws Exception {
        byte[] data = readInputStream(inStream);

        File imageFile = new File(folderPath + id + ".jpeg");
        FileOutputStream outStream = new FileOutputStream(imageFile);
        outStream.write(data);
        outStream.close();
    }

    private void createFolder() {
        Date date = new Date();
        String path = FOLDER_PATH_PREFIX + new SimpleDateFormat("yyyy/MM/dd").format(date);
        File folder = new File(path);
        if (!folder.exists())
            folder.mkdirs();
        folderPath = path + "/";
    }

    private String generateCodeId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private boolean validate(String number) {
        return Integer.valueOf(number) > 0;
    }
}
