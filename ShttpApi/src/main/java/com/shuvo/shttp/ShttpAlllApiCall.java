package com.shuvo.shttp;

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

/**
 * ShttpAlllApiCall is a utility class for making HTTP requests using OkHttp library.
 * It provides methods for both GET and POST requests with asynchronous callbacks.
 */
public class ShttpAlllApiCall {

    // Listener for handling API responses
    static ShttpallOnResponseListener shttpallOnResponseListener;

    /**
     * Initiates a GET request to the specified URL.
     *
     * @param url The URL to make the GET request to.
     * @param key A unique identifier for the request.
     */
    public static void getRequest(String url, String key) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        // Making the asynchronous call
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // Handling the failure and notifying the listener
                shttpallOnResponseListener.onApiResponse(null, key, 0, true, String.valueOf(e));
                e.printStackTrace();
                Log.e("ShttpAlllApiCall", String.valueOf(e));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                // Handling the response and notifying the listener
                try {
                    if (response.isSuccessful())
                        shttpallOnResponseListener.onApiResponse(response.body().string(), key, response.code(), false, null);
                } catch (Exception e) {
                    Log.e("ShttpAlllApiCall", String.valueOf(e));
                }
            }
        });
    }

    /**
     * Initiates a POST request to the specified URL with JSON data.
     *
     * @param url      The URL to make the POST request to.
     * @param postBody JSON data to be included in the request body.
     * @param key      A unique identifier for the request.
     */
    public static void postRequest(String url, String postBody, String key) {
        if (postBody == null) postBody = "{}";

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(postBody, MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder().url(url).post(requestBody).build();

        // Making the asynchronous call
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // Handling the failure and notifying the listener
                shttpallOnResponseListener.onApiResponse(null, key, 0, true, String.valueOf(e));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                // Handling the response and notifying the listener
                try {
                    if (response.isSuccessful())
                        shttpallOnResponseListener.onApiResponse(response.body().string(), key, response.code(), false, null);
                } catch (Exception e) {
                    Log.e("ShttpAlllApiCall", e.toString());
                }
            }
        });
    }

    /**
     * Interface for handling API responses.
     */
    public interface ShttpallOnResponseListener {
        /**
         * Callback method invoked when an API response is received.
         *
         * @param result       The result or response body of the API request.
         * @param key          A unique identifier for the request.
         * @param statusCode   The HTTP status code indicating the success of the request.
         * @param error        True if there is an error, false otherwise.
         * @param errorMessage The error message describing the cause of the failure (if any).
         */
        void onApiResponse(String result, String key, int statusCode, boolean error, String errorMessage);
    }
}
