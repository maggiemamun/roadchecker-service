package com.tfl.roadchecker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tfl.roadchecker.dto.RoadCheckerRequest;
import com.tfl.roadchecker.dto.RoadAPIResponse;
import com.tfl.roadchecker.exception.RoadCheckerException;

/**
 * This interface exposes the method(s) to check road status.
 */
public interface RoadCheckerService {
    /**
     * The purpose of this method is to check the roadstatus including severity description
     * and return the status as part of the RoadAPIResponse
     */

    public RoadAPIResponse getRoadStatus(final RoadCheckerRequest roadCheckerRequest) throws RoadCheckerException, JsonProcessingException;
}
