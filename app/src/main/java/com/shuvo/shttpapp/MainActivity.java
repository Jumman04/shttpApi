package com.shuvo.shttpapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shuvo.shttp.OnApiResponse;
import com.shuvo.shttp.ShttpApiCall;

/**
 * MainActivity represents the main activity of the application.
 * It demonstrates the usage of the ShttpApiCall class to make GET and POST requests.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Finding UI elements
        Button getRequestBtn = findViewById(R.id.get);
        TextView tv = findViewById(R.id.textView);
        Button postRequestBtn = findViewById(R.id.post);

        // Creating an instance of ShttpApiCall
        ShttpApiCall shttpApiCall = new ShttpApiCall();

        // Setting up a click listener for the GET request button
        getRequestBtn.setOnClickListener(view -> shttpApiCall.getRequest("https://jsonplaceholder.typicode.com/posts/1", new OnApiResponse() {
            @Override
            public void onSuccessResponse(String result, int statusCode) {
                // Updating the TextView with the successful response
                tv.setText(result);
            }

            @Override
            public void onFailure(String errorMessage) {
                // Updating the TextView with the failure message
                tv.setText(errorMessage);
            }

            @Override
            public void onErrorResponse(String errorMessage, int statusCode) {
                // Updating the TextView with the error response
                tv.setText(errorMessage);
            }
        }));

        // Setting up a click listener for the POST request button
        postRequestBtn.setOnClickListener(view -> shttpApiCall.postApiRequest("https://jsonplaceholder.typicode.com/posts/", new OnApiResponse() {
            @Override
            public void onSuccessResponse(String result, int statusCode) {
                // Updating the TextView with the successful response
                tv.setText(result);
            }

            @Override
            public void onFailure(String errorMessage) {
                // Updating the TextView with the failure message
                tv.setText(errorMessage);
            }

            @Override
            public void onErrorResponse(String errorMessage, int statusCode) {
                // Updating the TextView with the error response
                tv.setText(errorMessage);
            }
        }));
    }
}
