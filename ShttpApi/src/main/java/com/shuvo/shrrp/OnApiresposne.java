package com.shuvo.shrrp;

public interface OnApiresposne {
    void onSuccessResponse(String result, int statuscode);

    void onFailure(String errorMessage);

    void onErrorResponse(String errorMessage, int StatusCode);

}