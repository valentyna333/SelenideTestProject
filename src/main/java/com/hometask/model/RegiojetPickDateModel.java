package com.hometask.model;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Model of Regiojet Date Pick form : contains selectors like travel date and scroll buttons
 */
public final class RegiojetPickDateModel {

    public static final By DATE_CELL = By.className("react-datepicker__day");
    public static final SelenideElement NEXT_MONTH_BUTTON =  $(By.className("react-datepicker__navigation--next"));

    private RegiojetPickDateModel(){

    }
}
