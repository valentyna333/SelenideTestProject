package com.hometask.implementation;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.hometask.controller.RegiojetSearchResultDetailsController;
import org.openqa.selenium.JavascriptExecutor;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.hometask.model.RegiojetSearchResultDetailsModel.*;

/**
 * Regiojet Search Result Details Controller implementation
 */
public class RegiojetSearchResultDetailsControllerImpl implements RegiojetSearchResultDetailsController {

    private final SelenideElement parentForm;
    private final ElementsCollection cities;

    /**
     * Regiojet Search Result Details Controller initialization
     *
     * @param parentForm parent search result details form
     */
    public RegiojetSearchResultDetailsControllerImpl(SelenideElement parentForm) {
        this.parentForm = parentForm;
        cities = parentForm.$$(BOTH_CITIES);
    }

    @Override
    public SelenideElement getSearchResultDetailsTitle() {
        return parentForm.$(DAY_AND_DATE);
    }

    @Override
    public String getDestinationCity() {
        return splitCities(0)[0];
    }

    @Override
    public String getArrivalCity() {
        return splitCities(cities.size() - 1)[1];
    }

    @Override
    public void closeTripDetails() {
        //In case of indirect trips, simple click implementation is not working
        JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
        executor.executeScript(
                "document.getElementsByClassName('" + SEARCH_RECORD_ARROW_UP_CLASS + "')[0].click();");
    }

    @Override
    public boolean isTripDirect(){
        boolean directTrip = false;
        if (cities.size()==1){
            directTrip = true;
        }
        return directTrip;
    }


    /**
     * Split Destination and Arrival cities
     *
     * @param index index of result record in cites collection
     * @return
     */
    private String[] splitCities(int index) {
        return cities.get(index).getText().split("-");
    }
}
