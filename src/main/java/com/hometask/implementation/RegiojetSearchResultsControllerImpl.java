package com.hometask.implementation;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.hometask.controller.RegiojetSearchResultsController;
import com.hometask.model.RegiojetSearchResultsModel;

/**
 * Regiojet Search Results Controller implementation
 */
public class RegiojetSearchResultsControllerImpl implements RegiojetSearchResultsController {

    @Override
    public SelenideElement getSearchResultsTitle() {
        return RegiojetSearchResultsModel.SEARCH_RESULTS_TITLE.shouldBe(Condition.visible);
    }

    @Override
    public ElementsCollection getRecords() {
        return RegiojetSearchResultsModel.SEARCH_RESULT_RECORDS;
    }
}
