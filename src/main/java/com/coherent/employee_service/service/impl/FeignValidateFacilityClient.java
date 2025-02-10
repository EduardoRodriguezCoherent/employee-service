package com.coherent.employee_service.service.impl;

import com.coherent.employee_service.service.ValidateGymFacilityService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gym-club-service", path = "/api/gym-clubs")
@Service
public interface FeignValidateFacilityClient extends ValidateGymFacilityService {

    @Override
    @GetMapping(VALIDATE_FACILITY_ENDPOINT)
    boolean validateExpertiseArea(@RequestParam("clubId") Long clubId,
                                  @RequestParam("facilityId") Long facilityId);
}