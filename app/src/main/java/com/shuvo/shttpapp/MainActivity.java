package com.shuvo.shttpapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shuvo.shttp.OnApiresposne;
import com.shuvo.shttp.ShttpApiCall;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getRequestBtn = findViewById(R.id.get);
        TextView tv = findViewById(R.id.textView);
        Button postRequestBtm = findViewById(R.id.post);
        ShttpApiCall shttpApiCall = new ShttpApiCall();
        getRequestBtn.setOnClickListener(view -> shttpApiCall.getRequest("https://jsonplaceholder.typicode.com/posts/1", new OnApiresposne() {
            @Override
            public void onSuccessResponse(String result, int statuscode) {
                tv.setText(result);
            }

            @Override
            public void onFailure(String errorMessage) {
                tv.setText(errorMessage);

            }

            @Override
            public void onErrorResponse(String errorMessage, int StatusCode) {
                tv.setText(errorMessage);
            }
        }));

        postRequestBtm.setOnClickListener(view -> shttpApiCall.postApiRequest("https://jsonplaceholder.typicode.com/posts/", new OnApiresposne() {
            @Override
            public void onSuccessResponse(String result, int statuscode) {
                tv.setText(result);
            }

            @Override
            public void onFailure(String errorMessage) {
                tv.setText(errorMessage);

            }

            @Override
            public void onErrorResponse(String errorMessage, int StatusCode) {
                tv.setText(errorMessage);
            }
        }));
    }
}