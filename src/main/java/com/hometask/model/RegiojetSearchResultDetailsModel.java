package com.hometask.model;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Model of Regiojet Search Result Details form : contains selectors like travel date, cities and view arrows
 */
public final class RegiojetSearchResultDetailsModel {

    public static final SelenideElement SEARCH_RESULTS_DETAIL_PARENT_FORM = $(By.className("sections-wrap"));
    public static final By DAY_AND_DATE = By.className("date");
    public static final By BOTH_CITIES = By.className("cities");
    public static final String SEARCH_RECORD_ARROW_UP_CLASS = "arrow-up-wrapper";

    private RegiojetSearchResultDetailsModel() {

    }
}
