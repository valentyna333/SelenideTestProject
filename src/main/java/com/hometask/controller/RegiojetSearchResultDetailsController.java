package com.hometask.controller;

import com.codeborne.selenide.SelenideElement;

/**
 * Regiojet Search Result Details Controller allows to operate the details of each trip
 */
public interface RegiojetSearchResultDetailsController {

    /**
     * Get Search Result Details title with Day and Date of the trip
     *
     * @return title element
     */
    SelenideElement getSearchResultDetailsTitle();

    /**
     * Get Destination city
     *
     * @return city name
     */
    String getDestinationCity();

    /**
     * Get Arrival city
     *
     * @return city name
     */
    String getArrivalCity();

    /**
     * Show if trip is direct
     * @return
     */
    boolean isTripDirect();

    /**
     * Close trip details form
     */
    void closeTripDetails();

}
