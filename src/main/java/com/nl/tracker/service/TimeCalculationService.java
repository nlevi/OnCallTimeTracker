package com.nl.tracker.service;

import com.nl.tracker.model.ServiceRequest;

import java.util.List;

/**
 * Created by levin1 on 2017-02-16.
 */
public interface TimeCalculationService {

    int calculateTime(List<ServiceRequest> srs);

    int calculateTime(ServiceRequest sr);
}
