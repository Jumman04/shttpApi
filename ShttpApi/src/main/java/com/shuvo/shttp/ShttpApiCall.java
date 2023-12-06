package com.shuvo.shttp;

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

/**
 * ShttpApiCall is a utility class for making HTTP requests using OkHttp library.
 * It provides methods for both GET and POST requests with asynchronous callbacks.
 */
public class ShttpApiCall {
    private final OkHttpClient client;
    private final Handler mainHandler;

    /**
     * Constructor to initialize OkHttpClient and mainHandler.
     */
    public ShttpApiCall() {
        this.client = new OkHttpClient();
        this.mainHandler = new Handler(Looper.getMainLooper());

    }

    /**
     * Initiates a GET request to the specified URL.
     *
     * @param url           The URL to make the GET request to.
     * @param onApiResponse Callback for handling API response.
     */
    public void getRequest(String url, OnApiResponse onApiResponse) {
        getRequest(url, null, onApiResponse);
    }

    /**
     * Initiates a GET request to the specified URL with custom headers.
     *
     * @param url           The URL to make the GET request to.
     * @param headers       Custom headers for the request.
     * @param onApiResponse Callback for handling API response.
     */
    public void getRequest(String url, Headers headers, OnApiResponse onApiResponse) {
        // Building the request

        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (headers != null) requestBuilder.headers(headers);


        Request request = requestBuilder.build();

        // Making the asynchronous call
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // Posting the failure on the main thread
                mainHandler.post(() -> onApiResponse.onFailure(e.getMessage()));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                // Handling the response on the main thread
                if (response.isSuccessful()) {
                    mainHandler.post(() -> {
                        try {
                            // Passing the success response to the callback
                            onApiResponse.onSuccessResponse(response.body().string(), response.code());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } else
                    // Passing the error response to the callback
                    mainHandler.post(() -> {
                        try {
                            onApiResponse.onErrorResponse(response.body().string(),response.message(), response.code());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

            }
        });
    }

    /**
     * Initiates a POST request to the specified URL with JSON data.
     *
     * @param url           The URL to make the POST request to.
     * @param jsonData      JSON data to be included in the request body.
     * @param onApiResponse Callback for handling API response.
     */
    public void postApiRequest(String url, String jsonData, OnApiResponse onApiResponse) {
        postApiRequest(url, jsonData, null, onApiResponse);
    }

    // ... (similar comments for other overloaded methods)

    /**
     * Initiates a POST request to the specified URL with a callback for handling the response.
     *
     * @param url           The URL to make the POST request to.
     * @param onApiResponse Callback for handling API response.
     */
    public void postApiRequest(String url, OnApiResponse onApiResponse) {
        postApiRequest(url, null, null, onApiResponse);
    }

    /**
     * Initiates a POST request to the specified URL with a custom headers, and a callback for handling the response.
     *
     * @param url           The URL to make the POST request to.
     * @param headers       The custom headers.
     * @param onApiResponse Callback for handling API response.
     */
    public void postApiRequest(String url, Headers headers, OnApiResponse onApiResponse) {
        postApiRequest(url, null, headers, onApiResponse);
    }

    /**
     * Initiates a POST request to the specified URL with a request body, custom headers, and a callback for handling the response.
     *
     * @param url           The URL to make the POST request to.
     * @param body          The request body to be included in the POST request.
     * @param headers       Custom headers for the request.
     * @param onApiResponse Callback for handling API response.
     */
    public void postApiRequest(String url, String body, Headers headers, OnApiResponse onApiResponse) {
        if (body == null) body = "{}";

        // Creating the request body with JSON data
        RequestBody requestBody = RequestBody.create(body, MediaType.parse("application/json; charset=utf-8"));
        Request.Builder requestBuilder = new Request.Builder().url(url).post(requestBody);

        if (headers != null) requestBuilder.headers(headers);


        Request request = requestBuilder.build();

        // Making the asynchronous call
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // Posting the failure on the main thread
                mainHandler.post(() -> onApiResponse.onFailure(e.getMessage()));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                // Handling the response on the main thread
                if (response.isSuccessful()) {
                    mainHandler.post(() -> {
                        try {
                            // Passing the success response to the callback
                            onApiResponse.onSuccessResponse(response.body().string(), response.code());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } else
                    // Passing the error response to the callback
                    mainHandler.post(() -> {
                        try {
                            onApiResponse.onErrorResponse(response.body().string(),response.message(), response.code());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

            }
        });
    }
}
