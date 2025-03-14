package com.coherent.employee_service.service;

public interface ValidateGymFacilityService {

    String BASE_URI = "http://gym-club-service/api/gym-clubs"; // check for call from service name
    String VALIDATE_FACILITY_ENDPOINT = "/validateClubFacility";

    boolean validateExpertiseArea(Long clubId, Long facilityId);
}
