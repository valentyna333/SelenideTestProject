package com.hometask.controller;

import com.hometask.enums.Route;

/**
 * Regiojet Home Page controller is an initial controller, which allows search
 * connection with set Destination, Arrival places and Departure Time
 */
public interface RegiojetHomePageController {

    /**
     * Set Destination place
     *
     * @param route city name
     * @return this controller
     */
    RegiojetHomePageController withRouteFrom(Route route);

    /**
     * Set Arrival place
     *
     * @param route city name
     * @return this controller
     */
    RegiojetHomePageController withRouteTo(Route route);

    /**
     * Set Departure Time
     *
     * @return pick date controller
     */
    RegiojetPickDateController withDepartureTime();

    /**
     * Start search
     *
     * @return this controller
     */
    RegiojetSearchResultsController search();

}
