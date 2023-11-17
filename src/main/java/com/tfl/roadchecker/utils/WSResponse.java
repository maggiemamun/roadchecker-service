package com.tfl.roadchecker.utils;

public class WSResponse<R> {
    private ResponseHeaders responseHeaders;
    private int httpStatus;
    private R responseBody;

    public WSResponse() {
    }

    public WSResponse(ResponseHeaders responseHeaders, int httpStatus, R responseBody) {
        this.responseHeaders = responseHeaders;
        this.httpStatus = httpStatus;
        this.responseBody = responseBody;
    }

    public ResponseHeaders getResponseHeaders() {
        return responseHeaders;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public R getResponseBody() {
        return responseBody;
    }

    public void setResponseHeaders(ResponseHeaders responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setResponseBody(R responseBody) {
        this.responseBody = responseBody;
    }
}
