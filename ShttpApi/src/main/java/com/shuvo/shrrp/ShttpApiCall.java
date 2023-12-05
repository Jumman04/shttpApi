package com.shuvo.shrrp;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ShttpApiCall {
    private final OkHttpClient client;
    private final Handler mainHandler;

    public ShttpApiCall() {
        this.client = new OkHttpClient();
        this.mainHandler = new Handler(Looper.getMainLooper());

    }

    public void getRequest(String url, OnApiresposne onApiresposne) {
        getRequest(url, null, onApiresposne);
    }

    public void getRequest(String url, Headers headers, OnApiresposne onApiresposne) {
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (headers != null) {
            requestBuilder.headers(headers);
        }

        Request request = requestBuilder.build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                mainHandler.post(() -> onApiresposne.onFailure(e.getMessage()));

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (response.isSuccessful()) {
                    mainHandler.post(() -> {
                        try {
                            onApiresposne.onSuccessResponse(response.body().string(), response.code());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } else {
                    mainHandler.post(() -> onApiresposne.onErrorResponse(response.message(), response.code()));
                }
            }
        });
    }

    public void postApiRequest(String url, String jsondata, OnApiresposne onApiresposne) {
        postApiRequest(url, jsondata, null, onApiresposne);
    }

    public void postApiRequest(String url, OnApiresposne onApiresposne) {
        postApiRequest(url, null, null, onApiresposne);
    }

    public void postApiRequest(String url, Headers headers, OnApiresposne onApiresposne) {
        postApiRequest(url, null, headers, onApiresposne);
    }

    public void postApiRequest(String url, String Body, Headers headers, OnApiresposne onApiresposne) {
        if(Body == null){
            Body = "{}";
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), Body);
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .post(requestBody);

        if (headers != null) {
            requestBuilder.headers(headers);
        }

        Request request = requestBuilder.build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                mainHandler.post(() -> onApiresposne.onFailure(e.getMessage()));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    mainHandler.post(() -> {
                        try {
                            onApiresposne.onSuccessResponse(response.body().string(), response.code());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } else {
                    mainHandler.post(() -> onApiresposne.onErrorResponse(response.message(), response.code()));
                }
            }
        });
    }
}
