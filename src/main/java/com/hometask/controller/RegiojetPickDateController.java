package com.hometask.controller;

import com.hometask.enums.DayOfWeek;

/**
 * Regiojet Pick Date Controller allows set Departure Day
 */
public interface RegiojetPickDateController {

    /**
     * Set Departure Day
     *
     * @param dayOfWeek day of the week
     */
    void withDepartureDay(DayOfWeek dayOfWeek);

}
