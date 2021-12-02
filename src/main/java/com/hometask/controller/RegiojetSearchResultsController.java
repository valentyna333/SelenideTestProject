package com.hometask.controller;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

/**
 * Regiojet Search Results Controller allows to operate all available trips
 */
public interface RegiojetSearchResultsController {

    /**
     * Get Search Result Title (Day + Date values)
     *
     * @return title element
     */
    SelenideElement getSearchResultsTitle();

    /**
     * Get All trips
     *
     * @return collection with all records
     */
    ElementsCollection getRecords();
}
