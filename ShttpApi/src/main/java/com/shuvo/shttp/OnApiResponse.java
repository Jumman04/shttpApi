package com.shuvo.shttp;

/**
 * The OnApiResponse interface provides callback methods for handling API responses.
 */
public interface OnApiResponse {

    /**
     * Callback method invoked when the API request is successful.
     *
     * @param response     The response or response body of the successful API request.
     * @param statusCode The HTTP status code indicating the success of the request.
     */
    void onSuccessResponse(String response, int statusCode);

    /**
     * Callback method invoked when the API request encounters a failure.
     *
     * @param errorMessage The error message describing the cause of the failure.
     */
    void onFailure(String errorMessage);

    /**
     * Callback method invoked when the API request returns an error response.
     *
     * @param response    The response or response body of the error response.
     * @param errorMessage The error message provided by the API. statusCode The HTTP status code indicating the error response.
     * @param statusCode   The HTTP status code indicating the error response.
     */
    void onErrorResponse(String response,String errorMessage, int statusCode);
}
