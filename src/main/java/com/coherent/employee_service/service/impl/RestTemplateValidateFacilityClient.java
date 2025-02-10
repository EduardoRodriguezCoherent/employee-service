package com.coherent.employee_service.service.impl;

import com.coherent.employee_service.service.ValidateGymFacilityService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("RestTemplateValidateFacilityClient")
public class RestTemplateValidateFacilityClient implements ValidateGymFacilityService {

    private final RestTemplate restTemplate;

    public RestTemplateValidateFacilityClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean validateExpertiseArea(Long clubId, Long facilityId) {
        String url = String.format("%s%s?clubId=%d&facilityId=%d",
                BASE_URI, VALIDATE_FACILITY_ENDPOINT,
                clubId, facilityId);
        return restTemplate.getForObject(url, Boolean.class);
    }
}
