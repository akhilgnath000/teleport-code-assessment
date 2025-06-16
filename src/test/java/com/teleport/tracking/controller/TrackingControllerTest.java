package com.teleport.tracking.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.teleport.tracking.model.TrackingIdResponse;
import com.teleport.tracking.service.TrackingService;

@WebMvcTest(TrackingController.class)
public class TrackingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TrackingService trackingService;

	/**
	 * Positive test: Valid countries, service returns tracking info
	 * 
	 * @throws Exception
	 */
	@Test
	void testValidCountryCodes() throws Exception {
		double weight = 1.234;
		TrackingIdResponse mockResponse = new TrackingIdResponse("TRACK12345",
				String.valueOf(System.currentTimeMillis()));
		when(trackingService.generateTrackingNumber("US", "IN", weight, "2025-06-12T19:29:32+08:00",
				"de619854-b59b-425e-9db4-943979e1bd49", "RedBox Logistics", "redbox-logistics"))
				.thenReturn(mockResponse);

		mockMvc.perform(get("/api/tracking/v1/getTrackingId").param("origin_country_id", "US")
				.param("destination_country_id", "IN").param("weight", String.valueOf(weight))
				.param("created_at", "2025-06-12T19:29:32+08:00")
				.param("customer_id", "de619854-b59b-425e-9db4-943979e1bd49").param("customer_name", "RedBox Logistics")
				.param("customer_slug", "redbox-logistics")).andExpect(status().isOk());
	}

	/**
	 * 
	 * Negative test: Invalid origin country
	 * 
	 * @throws Exception
	 */
	@Test
	void testInvalidOriginCountryCode() throws Exception {
		double weight = 1.234;
		TrackingIdResponse mockResponse = new TrackingIdResponse("TRACK12345",
				String.valueOf(System.currentTimeMillis()));
		when(trackingService.generateTrackingNumber("US", "IN", weight, "2025-06-12T19:29:32+08:00",
				"de619854-b59b-425e-9db4-943979e1bd49", "RedBox Logistics", "redbox-logistics"))
				.thenReturn(mockResponse);

		mockMvc.perform(get("/api/tracking/v1/getTrackingId").param("origin_country_id", "ZZ")
				.param("destination_country_id", "IN").param("weight", String.valueOf(weight))
				.param("created_at", "2025-06-12T19:29:32+08:00")
				.param("customer_id", "de619854-b59b-425e-9db4-943979e1bd49").param("customer_name", "RedBox Logistics")
				.param("customer_slug", "redbox-logistics")).andExpect(status().isBadRequest());
	}

	/**
	 * Negative test: Invalid destination country
	 * 
	 * @throws Exception
	 */
	@Test
	void testInvalidDestinationCountryCode() throws Exception {
		double weight = 1.234;
		TrackingIdResponse mockResponse = new TrackingIdResponse("TRACK12345",
				String.valueOf(System.currentTimeMillis()));
		when(trackingService.generateTrackingNumber("US", "IN", weight, "2025-06-12T19:29:32+08:00",
				"de619854-b59b-425e-9db4-943979e1bd49", "RedBox Logistics", "redbox-logistics"))
				.thenReturn(mockResponse);

		mockMvc.perform(get("/api/tracking/v1/getTrackingId").param("origin_country_id", "US")
				.param("destination_country_id", "ABC").param("weight", String.valueOf(weight))
				.param("created_at", "2025-06-12T19:29:32+08:00")
				.param("customer_id", "de619854-b59b-425e-9db4-943979e1bd49").param("customer_name", "RedBox Logistics")
				.param("customer_slug", "redbox-logistics")).andExpect(status().isBadRequest());
	}

}
