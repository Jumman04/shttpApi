package com.shuvo.shttpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.shuvo.shrrp.ShttpAlllApiCall;

import org.json.JSONException;
import org.json.JSONObject;

public class shttofoorall extends AppCompatActivity implements ShttpAlllApiCall.ShttpallOnResponseListener {
    String getrequestkey = "qq";
    String podtrequestkey = "22";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shttofoorall);


        // a sinple get request
        ShttpAlllApiCall.getRequest("https://jsonplaceholder.typicode.com/posts", getrequestkey);

        // a sinple post request
        ShttpAlllApiCall.postRequest("https://jsonplaceholder.typicode.com/posts", "{\" body\": \"content \"}", "KEY");


    }


    @Override
    public void onApiResponse(String result, String key, int statusCpde, Boolean error, String errorMessage) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // resukt your  response in string format
                //key help you to identfy your request
                //statusCode your response status code

                if (error) {
                    //if url is wrong  , server error or  no inter connection so erroer is euqal true
                    //if error is true so you  get the error  message else it is null

                } else {
                    if (key.equals(getrequestkey)) {
                        //tis is yoir get request

                        //vonvert the  result to jsonpbject
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    } else if (key.equals(getrequestkey)) {
                        //tis is yoir post request
                        //vonvert the  result to jsonpbject
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                }


            }
        });
    }
}