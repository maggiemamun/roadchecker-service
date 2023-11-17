package com.tfl.roadchecker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tfl.roadchecker.ErrorHandler;
import com.tfl.roadchecker.dto.ErrorResponse;
import com.tfl.roadchecker.dto.RoadAPIResponse;
import com.tfl.roadchecker.dto.RoadCheckerRequest;
import com.tfl.roadchecker.exception.RoadCheckerException;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RoadCheckerServiceImpl implements RoadCheckerService {

    private final Logger logger = LoggerFactory.getLogger(RoadCheckerServiceImpl.class);

    @Value("${road-api.endpoint}")
    private String endpoint;
    @Value("${road-api.appId}")
    private String appid;
    @Value("${road-api.appkey}")
    private String appKey;
    RestTemplate restTemplate = new RestTemplate();


    /**
     * This service takes in :
     * 1-- roadId
     * 2-- calls TFL /Road api with road id and appkey(developer key)
     * 3-- returns road status based on road id being valid or non existent
     */
    @Override
    public RoadAPIResponse getRoadStatus(final RoadCheckerRequest roadCheckerRequest) throws RoadCheckerException, JsonProcessingException {

        final String roadApiUrl = endpoint + roadCheckerRequest.getRoadId();
        RoadAPIResponse roadAPIResponse = null;
        try {
            MultiValueMap<String, String> queryParam = getQueryParamData();
            ResponseEntity<String> result = restTemplate.exchange(UriComponentsBuilder.fromHttpUrl(roadApiUrl).queryParams(queryParam).build().toString(),
                    HttpMethod.GET, HttpEntity.EMPTY, String.class);
            roadAPIResponse = mapRoadAPIResponse(result);
        } catch (HttpStatusCodeException httpStatusCodeException) {

            ResponseEntity<String> errorResult = ResponseEntity.status(httpStatusCodeException.getRawStatusCode()).headers(httpStatusCodeException.getResponseHeaders())
                    .body(httpStatusCodeException.getResponseBodyAsString());
            logger.info("Error while calling Road api service:", errorResult.getBody().toString());
            roadAPIResponse = mapRoadAPIResponse(errorResult);

        } catch (Exception exp) {
            logger.info("Error while calling Road api service:", exp);
            throw new RoadCheckerException(RoadCheckerException.ERROR_RETRIEVING_ROAD_DETAILS);

        }
        logger.info("Response from Road api service ={}", roadAPIResponse.toString());
        return roadAPIResponse;
    }

    private MultiValueMap<String, String> getQueryParamData() {
        MultiValueMap<String, String> queryParam = new LinkedMultiValueMap<>();
        queryParam.add("app_id", appid);
        queryParam.add("app_key", appKey);
        return queryParam;
    }

    private RoadAPIResponse mapRoadAPIResponse(ResponseEntity<String> result) throws RoadCheckerException, JsonProcessingException {
        RoadAPIResponse[] response = new RoadAPIResponse[1];
        ObjectMapper mapper = new ObjectMapper();
        ErrorResponse errorResponse ;
        if (result != null) {
            if (isSuccessfulResponse(result.getStatusCode().value())) {
                response = mapper.readValue(result.getBody(), RoadAPIResponse[].class);
            } else {
                RoadAPIResponse roadAPIResponse = new RoadAPIResponse();
                response[0] = roadAPIResponse;
                errorResponse = mapper.readValue(result.getBody(), ErrorResponse.class);
                response[0].setErrorResponse(errorResponse);
            }
        }
        return response[0];
    }

    private static boolean isSuccessfulResponse(final int status) {
        return status == HttpStatus.OK.value();
    }

}
