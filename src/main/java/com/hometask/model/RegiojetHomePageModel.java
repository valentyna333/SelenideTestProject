package com.hometask.model;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Model of Regiojet Home Page : contains main selectors like travel routes, travel date inputs, search button, etc.
 */
public final class RegiojetHomePageModel {

    public static final SelenideElement ROUTE_FROM_INPUT = $(By.id("route-from"));
    public static final SelenideElement ROUTE_TO_INPUT = $(By.id("route-to"));
    public static final SelenideElement TRAVEL_DATE_INPUT = $(By.id("route-there-input"));
    public static final SelenideElement START_SEARCH_BUTTON = $(By.id("search-button"));

    private RegiojetHomePageModel() {

    }
}
