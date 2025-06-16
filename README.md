# Parcel Tracking Number Generator API

This is a Java Spring Boot application with API that generates a unique tracking number for a parcel using `UUID.randomUUID()`, which is having input parameters: `origin_country_id`, `destination_country_id`,'weight','created_at','customer_id','customer_name','customer_slug'.
---
## Features
- Generate globally unique tracking numbers.
- Stateless and lightweight REST API.
- Built with Spring Boot and Java 17+.
- UUID-based identifiers.
---

## Technologies Used
- Java 17+
- Spring Boot 3.5
- Spring Web
- Maven
- UUID (built-in Java)
---

## Input Parameters

| Parameter           | Type   | Description                |
|---------------------|--------|----------------------------|
| `originCountryId`      | String | ISO country code of origin |
| `destinationCountryId` | String | ISO country code of destination |
| 'weight'| double | weight in kilograms upto three decimal places |
| 'created_at' | String | Order creation timestamp in RFC 3339 format |
| 'customer_id' | String | Customer UUID |
| 'customer_name' | String | Customer's name |
| 'customer_slug' | String | Customer's name in slug-case |

---

## Example API Request
- localhost:8080/api/tracking/v1/getTrackingId?origin_country_id=CN&destination_country_id=IN&weight=1.234&created_at=2025-06-12T19:29:32+08:00&customer_id=de619854-b59b-425e-9db4-943979e1bd49&customer_name=RedBox Logistics&customer_slug=redbox-logistics

## Endpoint
- /api/tracking/v1/getTrackingId

  ## Response
-- Success Reponse --
{
    "trackingNumber": "TRK4C865ACB38D14",
    "createdAt": "2025-06-16T09:16:39.002672500Z"
}

## Failed Request and Response (Sending wrong destinationCountryCode)
- localhost:8080/api/tracking/v1/getTrackingId?origin_country_id=CN&destination_country_id=IND&weight=1.234&created_at=2025-06-12T19:29:32+08:00&customer_id=de619854-b59b-425e-9db4-943979e1bd49&customer_name=RedBox Logistics&customer_slug=redbox-logistics
-- Failed Response --
 Invalid destinationCountryId: must be a valid ISO 3166-1 alpha-2 code.

## Steps to Run application
- Checkout the project from the github to local
- Open the project in any IDE such as Eclipse, STS, VS etc
- Do maven clean and install the project.
- Run the TeleportCodeAssessmentApplication.java main class as a java application
- Once the application started please use the above API request to generate the random numbers.
- For running the junit test separate please run the test class as junit test.

## Project Details:
- The API will return the unique tracking numebr which is getting created randomly.
- For ensuring the uniqueness, generating the tracking number using UUID.randomUUID() method which will always maintain unique Id's
- Also implemented ConcurrentHashMap for temporary storing the tracking numbers generated and comparing with the newly created Id's which will give one more step of uniqueness.
- Using a ConcurrentHashMap can provide thread-safe, high-performance storage and lookup, especially when handling multiple requests concurrently. Also it also helped to remove the DB dependecies for now.
- Implemented the tracking number logic as part of service layer and also created DAO layers for future upgrades which will be useful for the DB data operations.
- As an initial step implemented the input parameters check for origin_country_id & destination_country_id which will always check for the valid country codes and null checks(As of now it is implemented in Controller which can be moved to seperate util class with other parameter validations) 
- Added exception handling by implementing custom exceptions which will return proper error messages for the incorrect parameters.
- Implemented junit testcases with positive and negatice scenarios by mocking the service method call. We can add more test cases based on the upgraded requirements.
  
