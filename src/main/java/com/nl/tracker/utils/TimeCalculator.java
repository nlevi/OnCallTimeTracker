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
        Calendar currentStart = Calendar.getInstance();
        Calendar currentEnd = Calendar.getInstance();
        Calendar previousEnd = Calendar.getInstance();
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
                previousEnd.setTime(previous.getEndTime());
                logger.info("Previous SR Time End Time: {}", previousEnd.getTime());
                logger.info("SR Time : {}", current.toString());
                currentStart.setTime(current.getStartTime());
                logger.info("Current SR Time Start Time: {}", currentStart.getTime());
                currentEnd.setTime(current.getEndTime());
                logger.info("Current SR Time End Time: {}", currentEnd.getTime());

                if ((previousEnd.get(Calendar.HOUR) == currentStart.get(Calendar.HOUR))
                        && (previousEnd.get(Calendar.DAY_OF_MONTH) != currentStart.get(Calendar.DAY_OF_MONTH))) {
                    //Same hour but different day
                    logger.info("Same hour, different day.");
                    if ((currentEnd.get(Calendar.MINUTE) == 0)
                            && (currentEnd.get(Calendar.HOUR) == (currentStart.get(Calendar.HOUR) + 1))) { //Check if end time is hh:00 and start time hour is 1 hour behind end. Then do count next hour.
                        logger.info("Hours : 1.");
                        hours.add(1);
                    } else if (currentEnd.get(Calendar.HOUR) == currentStart.get(Calendar.HOUR)) { //Check if start hour equals end hour.
                        logger.info("Hours : 1.");
                        hours.add(1);
                    } else if (currentEnd.get(Calendar.HOUR) > currentStart.get(Calendar.HOUR)) { // Start hour and end hour are different. Calculate difference + 1.
                        hours.add(((currentEnd.get(Calendar.HOUR) - currentStart.get(Calendar.HOUR)) + 1));
                    }
                } else if ((previousEnd.get(Calendar.HOUR) == currentStart.get(Calendar.HOUR))
                        && (previousEnd.get(Calendar.DAY_OF_MONTH) == currentStart.get(Calendar.DAY_OF_MONTH))) {
                    //Same hour, same day.
                    logger.info("Same hour, same day.");


                }
                //current = (ServiceRequestTime) iterator.next();
                //hours.add(overlapping(previous, current));
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
            logger.info("Overlapping with previous time.");
            logger.info("Previous ends at mins : {}", prevEnd.get(Calendar.MINUTE));
            if (prevEnd.get(Calendar.HOUR) == curEnd.get(Calendar.HOUR) && (curStart.get(Calendar.HOUR) == curEnd.get(Calendar.HOUR)) && (prevEnd.get(Calendar.MINUTE) != 0)) {
                logger.info("Hours diff 0. Same hour.");
                return 0;
            } else if (prevEnd.get(Calendar.HOUR) == curEnd.get(Calendar.HOUR) && (curStart.get(Calendar.HOUR) != curEnd.get(Calendar.HOUR)) && (curEnd.get(Calendar.HOUR) == (curStart.get(Calendar.HOUR) + 1))) {
                logger.info("Hours diff 0. Same hour.");
                return 0;
            } else if (prevEnd.get(Calendar.HOUR) == curEnd.get(Calendar.HOUR) && (curStart.get(Calendar.HOUR) == curEnd.get(Calendar.HOUR))) {
                logger.info("Hours diff : {}", 1);
                return 1;
            }
            /*else if (curStart.get(Calendar.HOUR) != curEnd.get(Calendar.HOUR)) {
                return getHours(curStart.getTime(), current.getEndTime());
            } */
            else {
                curStart.add(Calendar.HOUR, 1);
                return getHours(curStart.getTime(), curEnd.getTime());
            }
        } else {
            logger.info("No overlapping with previous time.");
            return getHours(current.getStartTime(), current.getEndTime());
        }

    }

    private static Integer getHours(Date startTime, Date endTime) {
        Calendar start = Calendar.getInstance();
        start.setTime(startTime);
        logger.info("Cal Start Time : {}", start.getTime());

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        logger.info("Cal End Time : {}", end.getTime());
        int t = 0;
        if (end.get(Calendar.HOUR) == start.get(Calendar.HOUR)) {
            logger.info("In same hour. Hours : 1");
            return 1;
        } else if (end.get(Calendar.HOUR) != start.get(Calendar.HOUR) && end.get(Calendar.MINUTE) == 0) {
            t = end.get(Calendar.HOUR) - start.get(Calendar.HOUR);
            logger.info("Hours diff : {}", t);
            return t;
        } else {
            t = end.get(Calendar.HOUR) - start.get(Calendar.HOUR);
            t = ++t;
            logger.info("Hours diff : {}", t);
            return t;
        }
    }
}
