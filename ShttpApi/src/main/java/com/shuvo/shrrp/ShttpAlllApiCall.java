package com.shuvo.shrrp;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ShttpAlllApiCall {
    static ShttpallOnResponseListener shttpallOnResponseListener;


    public static void getRequest(String url, String key) {


        //shttpOnResponseListener = (ShttpOnResponseListener) context;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                shttpallOnResponseListener.onApiResponse(null, key, 0000, true, e + "");
                e.printStackTrace();
                Log.e("112233", e + "");

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {


                    if (response.isSuccessful()) {
                        shttpallOnResponseListener.onApiResponse(response.body().string(), key, response.code(), false,
                                null);


                    }

                } catch (Exception e) {
                    Log.e("112233", e + "");

                }
            }
        });
    }

    public static void postRequest(String url, String postNody, String key) {
        if (postNody == null) {
            postNody = "{}";
        }
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), postNody);
        Request request = new Request.Builder()

                .url(url).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                shttpallOnResponseListener.onApiResponse(null, key, 0000, true, e + "");

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        shttpallOnResponseListener.onApiResponse(response.body().string(), key, response.code(), false, null);

                    }


                } catch (Exception e) {


                }
            }
        });

    }

    public interface ShttpallOnResponseListener {
        void onApiResponse(String result, String key, int statusCpde, Boolean error, String errorMessage);
    }

}
