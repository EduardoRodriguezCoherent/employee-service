package com.coherent.employee_service.service;

public interface ValidateGymFacilityService {

    String BASE_URI = "http://gym-club-service/api/gym-clubs";
    String VALIDATE_FACILITY_ENDPOINT = "/validateClubFacility";

    boolean validateExpertiseArea(Long clubId, Long facilityId);
}
