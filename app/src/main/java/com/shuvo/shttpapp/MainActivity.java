package com.shuvo.shttpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.shuvo.shrrp.ShttpApiCall;

import org.json.JSONException;
import org.json.JSONObject;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// a sinple get request
        ShttpApiCall.getRequest("https://jsonplaceholder.typicode.com/posts", "KEY", new ShttpApiCall.ShttpOnResponseListener() {
            @Override
            public void onApiResponse(String result, String key, int statusCpde, Boolean errorn, String errorMessage) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // resukt your  response in string format
                        //key help you to identfy your request
                        //statusCode your response status code

                        if(errorn){
                            //if url is wrong  , server error or  no inter connection so erroer is euqal true
                            //if error is true so you  get the error  message else it is null

                        }else {
                            //vonvert the  result to jsonpbject
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }


                    }
                });

            }
        });
        // a sinple post request
        ShttpApiCall.postRequest("https://jsonplaceholder.typicode.com/posts","{\"post  body or null ", "KEY", new ShttpApiCall.ShttpOnResponseListener() {
            @Override
            public void onApiResponse(String result, String key, int statusCpde, Boolean error, String errorMessage) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // resukt your  response in string format
                        //key help you to identfy your request
                        //statusCode your response status code

                        if(error){
                            //if url is wrong  , server error or  no inter connection so erroer is euqal true
                            //if error is true so you  get the error  message else it is null

                        }else {
                            //vonvert the  result to jsonpbject
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }


                    }
                });

            }
        });




    }
}