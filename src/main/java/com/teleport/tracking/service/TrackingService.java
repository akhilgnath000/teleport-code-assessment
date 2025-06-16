package com.teleport.tracking.service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.teleport.tracking.model.TrackingIdResponse;

@Service
public class TrackingService {
	private static final Logger logger = LogManager.getLogger(TrackingService.class);

	private final String TRACKING_ID_PREFIX = "TRK";

	private final ConcurrentHashMap<String, Boolean> generatedIds = new ConcurrentHashMap<>();

	public TrackingIdResponse generateTrackingNumber(String originCountryId, String destinationCountryId, double weight,
			String createdAt, String customerId, String customerName, String customerSlugName) {
		logger.info("TrackingService.generateTrackingNumber() : Started generating tracking number");

		String trackingNumber;

		do {
			String raw = TRACKING_ID_PREFIX + UUID.randomUUID().toString();
			trackingNumber = generateCode(raw);
		} while (generatedIds.putIfAbsent(trackingNumber, true) != null);

		return new TrackingIdResponse(trackingNumber, String.valueOf(System.currentTimeMillis()));
	}

	private String generateCode(String input) {
		return input.toUpperCase().replaceAll("[^A-Z0-9]", "").substring(0, Math.min(input.length(), 16));
	}

}
