package com.tfl.roadchecker.utils;

import java.util.Map;
import java.util.List;

public final class ResponseHeaders {
    private Map<String,List<String>> headers;

    public ResponseHeaders() {
    }

    public ResponseHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public Map<String, List<String>> getHeaders() {
        return this.headers;
    }
    public static ResponseHeaders create(final Map<String,List<String>> headers){
        return new ResponseHeaders(headers);
    }
}
