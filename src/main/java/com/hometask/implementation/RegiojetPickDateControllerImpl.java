package com.hometask.implementation;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.hometask.controller.RegiojetPickDateController;
import com.hometask.enums.DayOfWeek;
import com.hometask.model.RegiojetPickDateModel;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Regiojet Pick Date Controller implementation
 */
public class RegiojetPickDateControllerImpl implements RegiojetPickDateController {

    @Override
    public void withDepartureDay(DayOfWeek dayOfWeek) {
        SelenideElement firstAvailableDate = getFirstAvailableDateOnDay(dayOfWeek);
        if (null == firstAvailableDate) {
            RegiojetPickDateModel.NEXT_MONTH_BUTTON.click();
            firstAvailableDate = getFirstAvailableDateOnDay(dayOfWeek);
        }

        firstAvailableDate.click();
    }

    private SelenideElement getFirstAvailableDateOnDay(DayOfWeek dayOfWeek) {

        ElementsCollection daysOfTheMonth = Selenide.$$(RegiojetPickDateModel.DATE_CELL);

        SelenideElement firstAvailableDate = null;
        for (SelenideElement day : daysOfTheMonth) {
            String labelText = day.getAttribute("aria-label");
            if (labelText.contains("Not available")) {
                continue;
            } else if (labelText.contains(dayOfWeek.getDayNameInEn())) {
                firstAvailableDate = day;
                break;
            }
        }
        return firstAvailableDate;
    }
}
