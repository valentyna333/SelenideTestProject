package com.hometask.controller;

import java.util.Date;

/**
 * Regiojet Single Search Result Controller allows to operate each single trip
 */
public interface RegiojetSingleSearchResultController {

    /**
     * View trip details
     *
     * @return
     */
    RegiojetSearchResultDetailsController viewTripDetails();

    /**
     * Get amount of transfers
     *
     * @return amount of transfers number
     */
    int getTransfer();

    /**
     * Get Departure time
     *
     * @return departure time value
     */
    Date getDepartureTime();

    /**
     * Get Arrival time
     *
     * @return arrival time value
     */
    Date getArrivalTime();

    /**
     * Get Trip Duration
     *
     * @return trip duration in minutes
     */
    long getTripDurationInMinutes();

    /**
     * Get Price of the trip
     *
     * @return price number
     */
    double getPrice();
}
