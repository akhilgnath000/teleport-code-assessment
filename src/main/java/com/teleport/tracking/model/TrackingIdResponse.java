package com.teleport.tracking.model;

public class TrackingIdResponse {

	private String trackingNumber;
	private String createdAt;

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public TrackingIdResponse(String trackingNumber, String createdAt) {
		super();
		this.trackingNumber = trackingNumber;
		this.createdAt = createdAt;
	}

	public TrackingIdResponse() {
		super();
	}

	@Override
	public String toString() {
		return "TrackingIdResponse [trackingNumber=" + trackingNumber + ", createdAt=" + createdAt + "]";
	}

}
