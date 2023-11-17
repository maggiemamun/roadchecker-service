package com.tfl.roadchecker.roadchecker;

import com.tfl.roadchecker.dto.RoadAPIResponse;
import com.tfl.roadchecker.dto.RoadCheckerRequest;
import com.tfl.roadchecker.exception.ExceptionDetails;
import com.tfl.roadchecker.exception.RoadCheckerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoadCheckerApplicationTests {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	public static final String POST_REST_ENDPOINT = "/road-status/check";
	public static HttpHeaders httpHeaders;

	@Autowired
	private TestRestTemplate restTemplate;

	@org.junit.Test
	@DisplayName("Test to get the status of a  valid road - A2")
	public void testValidRoadStatus() {

		RoadCheckerRequest request = new RoadCheckerRequest();
		request.setRoadId("A2");
		HttpEntity<RoadCheckerRequest> entity = new HttpEntity<RoadCheckerRequest>(request, httpHeaders);
		ResponseEntity<String> responseEntity = restTemplate.exchange(POST_REST_ENDPOINT,  HttpMethod.POST, entity, String.class);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
		String message = responseEntity.getBody().toString();
		assertTrue(message.contains("The status of the A2 is Good and No Exceptional Delays"));
		log.info("testValidRoadStatus completed successfully with  response -" +message);
	}
	@org.junit.Test
	@DisplayName("Test to get the status another valid road - A20")
	public void testValidRoad2Status() {

		RoadCheckerRequest request = new RoadCheckerRequest();
		request.setRoadId("A20");
		HttpEntity<RoadCheckerRequest> entity = new HttpEntity<RoadCheckerRequest>(request, httpHeaders);
		ResponseEntity<String> responseEntity = restTemplate.exchange(POST_REST_ENDPOINT,  HttpMethod.POST, entity, String.class);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
		String message = responseEntity.getBody().toString();
		assertTrue(message.contains("The status of the A20 is Good and No Exceptional Delays"));
		log.info("testValidRoad2Status completed successfully with response -" + message);
	}
	@org.junit.Test
	@DisplayName("Test to get the status non existent road  - A203")
	public void testNonExistentRoadStatus() {

		RoadCheckerRequest request = new RoadCheckerRequest();
		request.setRoadId("A203");
		HttpEntity<RoadCheckerRequest> entity = new HttpEntity<RoadCheckerRequest>(request, httpHeaders);
		ResponseEntity<String> responseEntity = restTemplate.exchange(POST_REST_ENDPOINT,  HttpMethod.POST, entity, String.class);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
		String message = responseEntity.getBody().toString();
     	assertTrue(message.contains("A203 is not a valid road"));
		log.info("testNonExistentRoadStatus completed successfully with response -" + message);
	}

	@org.junit.Test
	@DisplayName("Test to get the status of an invalid road  - A2 0")
	public void testInvalidRoadStatus() {

		RoadCheckerRequest request = new RoadCheckerRequest();
		request.setRoadId("A2 0");
		HttpEntity<RoadCheckerRequest> entity = new HttpEntity<RoadCheckerRequest>(request, httpHeaders);
		ResponseEntity<String> responseEntity = restTemplate.exchange(POST_REST_ENDPOINT,  HttpMethod.POST, entity, String.class);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
		String message = responseEntity.getBody().toString();
		assertTrue(message.contains("A2 0 is not a valid road"));
		log.info("testInvalidRoadStatus completed successfully with response -" + message);
	}

	@org.junit.Test
	@DisplayName("Test to get the status when roadId is empty or not provided")
	public void testemptyRoadStatus() {

		RoadCheckerRequest request = new RoadCheckerRequest();
		request.setRoadId("");
		HttpEntity<RoadCheckerRequest> entity = new HttpEntity<RoadCheckerRequest>(request, httpHeaders);
		ResponseEntity<ExceptionDetails> response = restTemplate.exchange(POST_REST_ENDPOINT, HttpMethod.POST, entity, ExceptionDetails.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.BAD_REQUEST.value());
		String message = response.getBody().toString();
		assertTrue(message.contains("The road id is invalid"));
		log.info("testemptyRoadStatus completed successfully with response -" + message);
	}

	@org.junit.Test
	@DisplayName("Test to get the status when roadId is null ")
	public void testnullRoadStatus() {

		RoadCheckerRequest request = new RoadCheckerRequest();
		HttpEntity<RoadCheckerRequest> entity = new HttpEntity<RoadCheckerRequest>(request, httpHeaders);
		ResponseEntity<ExceptionDetails> response = restTemplate.exchange(POST_REST_ENDPOINT, HttpMethod.POST, entity, ExceptionDetails.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.BAD_REQUEST.value());
		String message = response.getBody().toString();
		assertTrue(message.contains("The road id is invalid"));
		log.info("testnullRoadStatus completed successfully with response -" + message);
	}



}

