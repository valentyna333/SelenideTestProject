package com.hometask.model;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Model of Regiojet Search Results form : contains selectors like title, price, destination, arrival, transfers and view trip arrows
 */
public final class RegiojetSearchResultsModel {

    public static final ElementsCollection SEARCH_RESULT_RECORDS = $$(By.xpath("//div[contains(@class,'connection-detail') " +
            "and not(contains(@class, 'disabled'))]"));
    //*[contains(@class, 'a') and not(contains(@class, 'b'))]
    //connection-detail
    // Condition.cssClass("disabled")
    public static final SelenideElement SEARCH_RESULTS_TITLE = $(By.className("day"));
    public static final By PRICE = By.id("price-yellow-desktop");
    public static final By TRAVEL_FROM_TO_TIME_CLASS = By.className("times");
    public static final By FROM_TO_TIME_AS_REGULAR_TEXT_CLASS = By.className("text-regular");
    public static final By AMOUNT_OF_TRANSFERS = By.id("transfers-desktop");
    public static final By SEARCH_RECORD_ARROW_DOWN_ID = By.id("detail-arrow-desktop");

    private RegiojetSearchResultsModel() {

    }
}
