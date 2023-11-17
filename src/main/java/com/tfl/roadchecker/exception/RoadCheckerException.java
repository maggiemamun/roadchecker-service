package com.tfl.roadchecker.exception;


/** Road related exception details mapping */
public class RoadCheckerException extends Exception {



    public static final String ROAD_ID_NOT_VALID= "The road id is invalid";
    public static final String ERROR_RETRIEVING_ROAD_DETAILS= "Error while calling Road api";




    public RoadCheckerException(final String message) {
        super(message);
    }



}
