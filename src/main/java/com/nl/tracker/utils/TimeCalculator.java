package com.nl.tracker.utils;

import com.nl.tracker.model.ServiceRequest;
import com.nl.tracker.model.ServiceRequestTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by levin1 on 2017-02-08.
 */
public class TimeCalculator {

    static final Logger logger = LoggerFactory.getLogger(TimeCalculator.class);

    public static int calculateHrs(List<ServiceRequestTime> times) {
        logger.info("Number of SR Times : {}", times.size());
        Iterator iterator = times.iterator();
        List<Integer> hours = new ArrayList<Integer>();
        ServiceRequestTime current;
        ServiceRequestTime previous = null;
        while (iterator.hasNext()) {
            current = (ServiceRequestTime) iterator.next();
            if (previous == null) {
                logger.info("This is first time entry.");
                logger.info("SR Time : {}", current.toString());
                logger.info("SR Time Start Time: {}", current.getStartTime());
                logger.info("SR Time End Time: {}", current.getEndTime());
                hours.add(getHours(current.getStartTime(), current.getEndTime()));
                previous = current;
            } else {
                logger.info("Previous SR Time : {}", previous.toString());
                logger.info("SR Time : {}", current.toString());
                logger.info("SR Time Start Time: {}", current.getStartTime());
                logger.info("SR Time End Time: {}", current.getEndTime());
                //current = (ServiceRequestTime) iterator.next();
                hours.add(overlapping(previous, current));
                previous = current;
            }

        }

        int sum = 0;
        for (Integer i : hours) {
            sum = sum + i;
        }

        return sum;
    }

    private static Integer overlapping(ServiceRequestTime previous, ServiceRequestTime current) {
        logger.info("Overlapping, previous : {} ", previous.getEndTime());
        logger.info("Overlapping, current : {} ", current.getStartTime());
        Calendar curStart = Calendar.getInstance();
        curStart.setTime(current.getStartTime());
        Calendar prevEnd = Calendar.getInstance();
        prevEnd.setTime(previous.getEndTime());
        Calendar curEnd = Calendar.getInstance();
        curEnd.setTime(current.getEndTime());

        if ((prevEnd.get(Calendar.HOUR) == curStart.get(Calendar.HOUR)) && (prevEnd.get(Calendar.DAY_OF_MONTH) == curStart.get(Calendar.DAY_OF_MONTH))) {
            logger.info("Overlapping with previous time. Incrementing by hour.");
            if (prevEnd.get(Calendar.HOUR) == curEnd.get(Calendar.HOUR)) {
                return 0;
            } else if (curStart.get(Calendar.HOUR) == curEnd.get(Calendar.HOUR)) {
                return getHours(curStart.getTime(), current.getEndTime());
            } else {
                curStart.add(Calendar.HOUR, 1);
                return getHours(curStart.getTime(), curEnd.getTime());
            }
        } else {
            logger.info("No overlapping with previous time.");
            return getHours(current.getStartTime(), current.getEndTime());
        }

    }

    private static Integer getHours(Date startTime, Date endTime) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(startTime);
        logger.info("Cal Start Time : {}", cal1.getTime());

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(endTime);
        logger.info("Cal End Time : {}", cal2.getTime());
        int t = 0;
        if (cal2.get(Calendar.HOUR) == cal1.get(Calendar.HOUR)) {
            logger.info("In same hour. Hours : 1");
            return 1;
        } else {

            t = cal2.get(Calendar.HOUR) - cal1.get(Calendar.HOUR);
            t = ++t;
            logger.info("Hours diff : {}", t);
            return t;
        }
    }
}
