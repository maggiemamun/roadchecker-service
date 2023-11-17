package com.tfl.roadchecker.controller;

import com.tfl.roadchecker.dto.RoadCheckerRequest;
import com.tfl.roadchecker.dto.RoadAPIResponse;
import com.tfl.roadchecker.dto.RoadCheckerResponse;
import com.tfl.roadchecker.service.RoadCheckerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.tfl.roadchecker.constants.Constants.*;

@RestController
@RequestMapping(value = "/road-status")
public class RoadCheckerController {
    @Autowired
    private RoadCheckerService roadCheckerService;

    private final Logger logger = LoggerFactory.getLogger(RoadCheckerController.class);

    /**
     * This service will accecpt a road id , call  tfl /Road api and return the road status .
     *
     * @param roadCheckerRequest
     * @return RoadCheckerResponse
     */
    @RequestMapping(
            path = "/check",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RoadCheckerResponse> retrieveRoadStatus(@Valid @RequestBody final RoadCheckerRequest roadCheckerRequest) throws Exception {
        logger.info("Inside Controller to check the road status for the road  ={}", roadCheckerRequest.getRoadId());
        final RoadAPIResponse roadAPIResponse = roadCheckerService.getRoadStatus(roadCheckerRequest);
        final RoadCheckerResponse roadCheckerResponse = new RoadCheckerResponse();
        String roadId = roadCheckerRequest.getRoadId();

        if (roadAPIResponse.getErrorResponse() != null) {
            roadCheckerResponse.setResponse(roadId + INVALID_RESP);
        } else {
            roadCheckerResponse.setResponse(SUCCESS_RESP_INIT + roadAPIResponse.getDisplayName() + " is " + roadAPIResponse.getStatusSeverity() + " and " + roadAPIResponse.getStatusSeverityDescription()+"."
            );
        }

        return new ResponseEntity<RoadCheckerResponse>(roadCheckerResponse, HttpStatus.OK);
    }

}
