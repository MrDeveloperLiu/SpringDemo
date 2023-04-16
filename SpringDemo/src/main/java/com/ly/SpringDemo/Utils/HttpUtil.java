package com.ly.SpringDemo.Utils;

import okhttp3.*;
import java.util.HashMap;

public class HttpUtil {
    public static MediaType MediaType_UrlEncoded = MediaType.parse("application/x-www-form-urlencoded");
    private static OkHttpClient httpClient = new OkHttpClient();

    private static String getURLEncodedFromMap(HashMap<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = params.size();
        int i = 0;
        params.forEach((k, v) -> {
            stringBuilder.append(String.format("%s=%s", k, v));
            if (i != size - 1) {
                stringBuilder.append("&");
            }
        });
        return stringBuilder.toString();
    }

    private static String resloveGetUrl(String url, HashMap<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url);
        int size = params.size();
        if (!url.contains("?") && size > 0) {
            stringBuilder.append("?");
        }
        int i = 0;
        params.forEach((k, v) -> {
            stringBuilder.append(String.format("%s=%s", k, v));
            if (i != size - 1) {
                stringBuilder.append("&");
            }
        });
        return stringBuilder.toString();
    }

    public static String post(String url, HashMap<String, String> params) {
        String urlEncodeString = getURLEncodedFromMap(params);
        RequestBody body = RequestBody.create(MediaType_UrlEncoded, urlEncodeString);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
            return String.format("Code:%d Msg:%s", response.code(), response.message());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static String get(String url, HashMap<String, String> params) {
        String newUrl = resloveGetUrl(url, params);
        Request request = new Request.Builder().url(newUrl).get().build();
        try {
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
            return String.format("Code:%d Msg:%s", response.code(), response.message());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
