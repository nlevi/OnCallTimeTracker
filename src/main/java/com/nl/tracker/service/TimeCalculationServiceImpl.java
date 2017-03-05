package com.nl.tracker.service;

import com.nl.tracker.model.ServiceRequest;
import com.nl.tracker.utils.TimeCalculator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by levin1 on 2017-02-16.
 */
public class TimeCalculationServiceImpl implements TimeCalculationService {
    @Autowired
    ServiceRequestTimeService serviceRequestTimeService;

    @Override
    public int calculateTime(List<ServiceRequest> srs) {
        int hours = 0;
        for (ServiceRequest sr : srs) {
            if (sr instanceof ServiceRequest) {
                hours = hours + TimeCalculator.calculateHrs(serviceRequestTimeService.findBySr(sr));
            }
        }
        return hours;
    }

    @Override
    public int calculateTime(ServiceRequest sr) {
        return TimeCalculator.calculateHrs(serviceRequestTimeService.findBySr(sr));
    }
}
