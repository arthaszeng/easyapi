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
@RequestMapping("/qrcode")
public class QRCoderController {
//        private static final String FETCH_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxfd65148b63fb1c13&secret=3b10c329e0c5f2874f53eab5ccf0a792";
    private static final String FETCH_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx1308670b4c3b323a&secret=93fd49edfcf1e57d4a28cb5e0bd0fe2d";
    private static final String URL_WX_CODE_PREFIX = "http://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";
    private static final String URL_QR_CODE_PREFIX = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=";
    private static final String FOLDER_PATH_PREFIX = "/Users/cwzeng/Documents/QRCode/";
    private static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};
    private String folderPath;
    private String token;

    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    @RequestMapping("/single")
    @ApiOperation(notes = "Create a batch of QR subcodes", value = "number", httpMethod = "GET")
    public String createSingleQRCodes(@RequestParam @ApiParam int number) throws Exception {
        createFolder(FOLDER_PATH_PREFIX + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        getToken();
        if (validate(number, 1)) {
            for (int i = 0; i < number; i++) {
                generateSubCodes(generateShortUuid(), generateShortUuid());
            }
            return "Ok";
        } else {
            return "Wrong parameters: number";
        }
    }

    @RequestMapping("/mixed")
    @ApiOperation(notes = "Create parent codes and subcodes", value = "number", httpMethod = "GET")
    public String createMixedQRCodes(@RequestParam @ApiParam int numOfParents, @RequestParam @ApiParam int scale) throws Exception {
        String topPath = FOLDER_PATH_PREFIX + new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        createFolder(topPath);
        getToken();

        if (validate(numOfParents, scale)) {
            for (int i = 0; i < numOfParents; i++) {
                String parentId = generateShortUuid();
                createFolder(topPath + "/" + String.valueOf(i));
                generateParentCodes(parentId, parentId);
                for (int y = 0; y < scale; y++) {
                    String subId = generateShortUuid();
                    generateSubCodes(parentId, subId);
                }
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

    private void generateSubCodes(String parentId, String subId) throws Exception {
        String dataJSON = getDataJSON(parentId, subId);
        String fileName = parentId + "_" + subId;
        sendPost(getWXCodeUrl(), dataJSON, fileName);
    }

    private void generateParentCodes(String parentId, String subId) throws Exception {

        String dataJSON = "{\n\"path\": \"pages/management/management?scene=" + parentId + "_" + subId + "\"\n}";
        String fileName = parentId + "_" + subId;
        sendPost(getQRCodeUrl(), dataJSON, fileName);
    }

    private String getWXCodeUrl() throws Exception {
        return URL_WX_CODE_PREFIX + token;
    }

    private String getQRCodeUrl() throws Exception {
        return URL_QR_CODE_PREFIX + token;
    }

    private void getToken() throws Exception {
        System.out.println("Start fetch token");
        token = sendGet().split(",")[0].split(":")[1].replace("\"", "");
        System.out.println("get token: " + token);
    }

    private String sendGet() throws Exception {
        URL obj = new URL(FETCH_TOKEN_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", USER_AGENT);

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();

        System.out.println(response.toString());
        return response.toString();
    }

    private String getDataJSON(String subId, String parentId) {
        return "{\n\"scene\":\"" + subId + "_" + parentId + "\"\n}";
    }

    private void sendPost(String urlStr, String dataJSON, String fileName) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");

        OutputStream os = conn.getOutputStream();
        os.write(dataJSON.getBytes("UTF-8"));
        os.close();

        System.out.println(conn.getResponseCode());
        InputStream inStream = conn.getInputStream();
        saveFile(fileName, inStream);
    }

    private void saveFile(String id, InputStream inStream) throws Exception {
        byte[] data = readInputStream(inStream);

        File imageFile = new File(folderPath + id + ".jpeg");
        FileOutputStream outStream = new FileOutputStream(imageFile);
        outStream.write(data);
        outStream.close();
    }

    private void createFolder(String path) {
        File folder = new File(path);
        if (!folder.exists())
            folder.mkdirs();
        folderPath = path + "/";
    }

    private String generateCodeId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private boolean validate(int parentNum, int scale) {
        return parentNum > 0 && scale > 0;
    }
}
