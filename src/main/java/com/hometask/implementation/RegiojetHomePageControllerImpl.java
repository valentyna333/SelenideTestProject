package com.hometask.implementation;

import com.hometask.controller.RegiojetHomePageController;
import com.hometask.controller.RegiojetPickDateController;
import com.hometask.controller.RegiojetSearchResultsController;
import com.hometask.enums.Route;
import com.hometask.model.RegiojetHomePageModel;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.page;

/**
 * Regiojet Home Page Controller implementation
 */
public class RegiojetHomePageControllerImpl implements RegiojetHomePageController {

    private final WebDriver driver;

    /**
     * Regiojet Home Page Controller initialization
     * @param driver Web Driver
     */
    public RegiojetHomePageControllerImpl(WebDriver driver) {
        this.driver = driver;
        driver.manage().window().maximize();
    }

    @Override
    public RegiojetHomePageController withRouteFrom(Route route) {
        RegiojetHomePageModel.ROUTE_FROM_INPUT.sendKeys(route.getRouteName() + Keys.ENTER);
        return this;
    }

    @Override
    public RegiojetHomePageController withRouteTo(Route route) {
        RegiojetHomePageModel.ROUTE_TO_INPUT.sendKeys(route.getRouteName() + Keys.ENTER);
        return this;
    }

    @Override
    public RegiojetPickDateController withDepartureTime() {
        RegiojetHomePageModel.TRAVEL_DATE_INPUT.click();
        return page(RegiojetPickDateControllerImpl.class);
    }

    @Override
    public RegiojetSearchResultsController search() {
        RegiojetHomePageModel.START_SEARCH_BUTTON.click();
        return page(RegiojetSearchResultsControllerImpl.class);
    }
}
