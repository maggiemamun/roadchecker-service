package com.tfl.roadchecker;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.io.IOException;
import java.net.URI;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class ErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return (
                clientHttpResponse.getStatusCode().series() == CLIENT_ERROR
                        || clientHttpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        if (clientHttpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR){
             throw HttpServerErrorException.create(clientHttpResponse.getStatusCode(), clientHttpResponse.getStatusText(), clientHttpResponse.getHeaders(), null, null);
        }


        else if (clientHttpResponse.getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR){
            throw HttpClientErrorException.create(clientHttpResponse.getStatusCode(), clientHttpResponse.getStatusText(), clientHttpResponse.getHeaders(), null, null);
        }
            if (clientHttpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                //create custom exception

            }


    }


}
