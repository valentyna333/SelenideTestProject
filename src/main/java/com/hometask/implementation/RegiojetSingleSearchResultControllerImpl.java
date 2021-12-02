package com.hometask.implementation;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.hometask.controller.RegiojetSearchResultDetailsController;
import com.hometask.controller.RegiojetSingleSearchResultController;
import com.hometask.model.RegiojetSearchResultsModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.hometask.model.RegiojetSearchResultDetailsModel.SEARCH_RESULTS_DETAIL_PARENT_FORM;

/**
 * Regiojet Single Search Result Controller implementation
 */
public class RegiojetSingleSearchResultControllerImpl implements RegiojetSingleSearchResultController {

    private final SelenideElement record;
    private final ElementsCollection departureAndArrivalTime;

    private static final SimpleDateFormat formatter = new SimpleDateFormat("H:m");

    /**
     * Regiojet Single Search Result Controller initialization
     *
     * @param record one single record in search results
     */
    public RegiojetSingleSearchResultControllerImpl(SelenideElement record) {
        this.record = record;
        departureAndArrivalTime = record.$(RegiojetSearchResultsModel.TRAVEL_FROM_TO_TIME_CLASS).$$(RegiojetSearchResultsModel.FROM_TO_TIME_AS_REGULAR_TEXT_CLASS);
    }

    @Override
    public RegiojetSearchResultDetailsController viewTripDetails() {
        record.$(RegiojetSearchResultsModel.SEARCH_RECORD_ARROW_DOWN_ID).shouldBe(Condition.visible).click();
        return new RegiojetSearchResultDetailsControllerImpl(SEARCH_RESULTS_DETAIL_PARENT_FORM);
    }

    @Override
    public int getTransfer() {
        return Integer.valueOf(record.$(RegiojetSearchResultsModel.AMOUNT_OF_TRANSFERS).getText().split(":")[1].trim());
    }

    @Override
    public Date getDepartureTime() {
        return parseTime(departureAndArrivalTime.get(0));
    }

    @Override
    public Date getArrivalTime() {
        return parseTime(departureAndArrivalTime.get(1));
    }

    @Override
    public long getTripDurationInMinutes() {
        long durationInMillis = Math.abs(getArrivalTime().getTime() - getDepartureTime().getTime());
        return TimeUnit.MINUTES.convert(durationInMillis, TimeUnit.MILLISECONDS);
    }

    @Override
    public double getPrice() {
        String priceAsText = record.$(RegiojetSearchResultsModel.PRICE).getText();
        priceAsText = priceAsText
                .replace(",", ".")
                .replace("od", "")
                .replaceAll("[^\\d.]", "");
        return Double.valueOf(priceAsText);
    }

    /**
     * Take and convert time to Date instance
     *
     * @param time element with time
     * @return time as Date
     */
    private Date parseTime(SelenideElement time) {
        Date tripTime;
        try {
            tripTime = formatter.parse(time.getText());

        } catch (ParseException e) {
            throw new IllegalArgumentException
                    ("Time : " + time.getText() +
                            " can't be converted to Date using format: [" + formatter.toPattern() + "]");
        }
        return tripTime;
    }
}
