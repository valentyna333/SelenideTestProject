package com.hometask.enums;

/**
 * Possible values of day of the week in SK
 */
public enum DayOfWeek {

    MONDAY("Monday", "Pondelok");

    private final String dayNameInEn;
    private final String dayNameInSk;

    DayOfWeek(String dayNameInEn, String dayNameInSk) {
        this.dayNameInEn = dayNameInEn;
        this.dayNameInSk = dayNameInSk;
    }

    public String getDayNameInEn() {
        return dayNameInEn;
    }

    public String getDayNameInSk() {
        return dayNameInSk;
    }

}
