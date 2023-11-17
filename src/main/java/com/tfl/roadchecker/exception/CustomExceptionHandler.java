package com.tfl.roadchecker.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class CustomExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Handles invalid data input related exceptions
     */
    @ExceptionHandler(RoadCheckerException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionDetails handleInputException(final RoadCheckerException roadCheckerException,
                                          final HttpServletRequest request) {
        logger.error("RoadCheckerException: {}", roadCheckerException.getMessage());
        return new ExceptionDetails(roadCheckerException.getMessage(), request.getRequestURI());
    }

    /**
     * Handles empty data with null pointer exceptions
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionDetails handleEmptyDataExceptions(final MethodArgumentNotValidException ex,
                                                                    final HttpServletRequest request) {
        logger.error("Empty or null input Exception: {}", ex.getMessage());
        return new ExceptionDetails(RoadCheckerException.ROAD_ID_NOT_VALID, request.getRequestURI());

    }

    /**
     * Handles all other server and application related exceptions
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ExceptionDetails handleAllGenericExceptions(final Exception ex,
                                                                     final HttpServletRequest request) {
        logger.error("Genric Exception: {}", ex);
        return new ExceptionDetails(ex.getMessage(), request.getRequestURI());
    }

}

