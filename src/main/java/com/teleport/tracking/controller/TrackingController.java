package com.teleport.tracking.controller;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teleport.tracking.exception.InvalidParameterException;
import com.teleport.tracking.model.TrackingIdResponse;
import com.teleport.tracking.service.TrackingService;

/**
 * @author akhil
 * @project teleport
 * 
 */
@RestController
@RequestMapping("api/tracking/v1")
public class TrackingController {

	private static final Logger logger = LogManager.getLogger(TrackingController.class);

	@Autowired
	private TrackingService trackingService;

	/**
	 * API for generating the unique tracking number
	 * 
	 * @param originCountryId
	 * @param destinationCountryId
	 * @param weight
	 * @param createdAt
	 * @param customerId
	 * @param customerName
	 * @param customerSlugName
	 * @return trackingNumber
	 */
	@GetMapping("/getTrackingId")
	public TrackingIdResponse getTrackingNumber(@RequestParam(value = "origin_country_id") String originCountryId,
			@RequestParam(value = "destination_country_id") String destinationCountryId,
			@RequestParam(value = "weight") double weight, @RequestParam(value = "created_at") String createdAt,
			@RequestParam(value = "customer_id") String customerId,
			@RequestParam(value = "customer_name") String customerName,
			@RequestParam(value = "customer_slug") String customerSlugName) {
		logger.info(
				"TrackingController.getTrackingNumber() Start - origin:{}, destination :{}, weight :{}, customerId :{}, customerName :{}, customerSlugName :{}",
				originCountryId, destinationCountryId, weight, customerId, customerName, customerSlugName);
		if (!isValidCountryCode(originCountryId)) {
			throw new InvalidParameterException("Invalid originCountryId: must be a valid ISO 3166-1 alpha-2 code.");
		}
		if (!isValidCountryCode(destinationCountryId)) {
			throw new InvalidParameterException(
					"Invalid destinationCountryId: must be a valid ISO 3166-1 alpha-2 code.");
		}
		TrackingIdResponse resp = trackingService.generateTrackingNumber(originCountryId, destinationCountryId, weight,
				createdAt, customerId, customerName, customerSlugName);
		logger.info("TrackingController.getTrackingNumber() End ");
		return resp;
	}

	private boolean isValidCountryCode(String code) {
		if (code == null || code.length() != 2)
			return false;

		String upperCode = code.toUpperCase();
		String[] isoCountries = Locale.getISOCountries();

		for (String country : isoCountries) {
			if (country.equals(upperCode))
				return true;
		}

		return false;
	}
}
