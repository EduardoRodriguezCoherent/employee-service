package com.coherent.employee_service.service.impl;

import com.coherent.employee_service.service.ValidateGymFacilityService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service("WebClientValidateFacilityClient")
public class WebClientValidateFacilityClient implements ValidateGymFacilityService {

    private final WebClient webClient;

    public WebClientValidateFacilityClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URI).build();
    }

    @Override
    public boolean validateExpertiseArea(Long clubId, Long facilityId) {
        return Boolean.TRUE.equals(webClient.get()
                .uri(uriBuilder -> uriBuilder.path(VALIDATE_FACILITY_ENDPOINT)
                        .queryParam("clubId", clubId)
                        .queryParam("facilityId", facilityId)
                        .build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());
    }
}