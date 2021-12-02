package com.hometask.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.hometask.controller.RegiojetHomePageController;
import com.hometask.controller.RegiojetSearchResultDetailsController;
import com.hometask.controller.RegiojetSearchResultsController;
import com.hometask.controller.RegiojetSingleSearchResultController;
import com.hometask.enums.DayOfWeek;
import com.hometask.enums.Route;
import com.hometask.implementation.RegiojetHomePageControllerImpl;
import com.hometask.implementation.RegiojetSingleSearchResultControllerImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertTrue;

/**
 * JUnit + Selenide tests
 */
public class GetOptimalConnectionsUITest {

    private static final DayOfWeek TRIP_DAY = DayOfWeek.MONDAY;
    private static final Route TRIP_FROM = Route.OSTRAVA;
    private static final Route TRIP_TO = Route.BRNO;
    private static final SimpleDateFormat formatter = new SimpleDateFormat("H:m");

    //Used for optimal connection calculations (<exactRecord, exactRecord value> pair)
    private static final Map<RegiojetSingleSearchResultController, Date> elementsWithDepartureTime = new HashMap<>();
    private static final Map<RegiojetSingleSearchResultController, Date> elementsWithArrivalTime = new HashMap<>();
    private static final Map<RegiojetSingleSearchResultController, Integer> elementsWithAmountOfTransfers = new HashMap<>();
    private static final Map<RegiojetSingleSearchResultController, Double> elementsWithPrices = new HashMap<>();
    private static final Map<RegiojetSingleSearchResultController, Long> elementsWithTripDuration = new HashMap<>();

    @BeforeAll
    public static void setup() {

        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver93\\chromedriver.exe");
        System.setProperty("selenide.browser", "Chrome");

        open("https://shop.regiojet.sk/");

        getDataForCalculation();
    }

    private static void getDataForCalculation() {

        RegiojetHomePageController homePage = new RegiojetHomePageControllerImpl(getWebDriver());

        homePage.withRouteFrom(TRIP_FROM)
                .withRouteTo(TRIP_TO)
                .withDepartureTime()
                .withDepartureDay(TRIP_DAY);

        RegiojetSearchResultsController searchResults = homePage.search();

        //Verification of title with search results
        searchResults.getSearchResultsTitle()
                .shouldHave(Condition.match("Verify title contains correct day",
                        title -> title.getText().contains(TRIP_DAY.getDayNameInSk())));

        //All found records
        ElementsCollection allFoundRecords = searchResults.getRecords();
        //Collecting parameters for verification
        for (SelenideElement record : allFoundRecords) {
            RegiojetSingleSearchResultController singleRecord = new RegiojetSingleSearchResultControllerImpl(record);

            int transferAmount = singleRecord.getTransfer();
            elementsWithDepartureTime.put(singleRecord, singleRecord.getDepartureTime());
            elementsWithArrivalTime.put(singleRecord, singleRecord.getArrivalTime());
            elementsWithTripDuration.put(singleRecord, singleRecord.getTripDurationInMinutes());
            elementsWithAmountOfTransfers.put(singleRecord, transferAmount);
            elementsWithPrices.put(singleRecord, singleRecord.getPrice());

            //View details of every route
            RegiojetSearchResultDetailsController tripDetails = singleRecord.viewTripDetails();

            //Verify Day of the trip in Details form
            tripDetails.getSearchResultDetailsTitle().shouldHave(Condition.match("Verify record contains correct day", day ->
                    day.getText().contains(TRIP_DAY.getDayNameInSk())));

            //Verify if route is direct or with transfers in Details form
            if (tripDetails.isTripDirect()) {
                assertTrue("Trip should have no transfers", transferAmount == 0);
            } else {
                assertTrue("Trip should have transfers", transferAmount > 0);
            }
            assertTrue("Trip contains incorrect Destination", tripDetails.getDestinationCity().contains(TRIP_FROM.getRouteName()));
            assertTrue("Trip contains incorrect Arrival", tripDetails.getArrivalCity().contains(TRIP_TO.getRouteName()));

            tripDetails.closeTripDetails();

        }
    }

    @Test
    public void getOptimalArrivalTime() {

        Stream<Map.Entry<RegiojetSingleSearchResultController, Date>> sortedArrivalDates =
                elementsWithArrivalTime.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue());

        System.out.println("The earliest Arrival Time : " +
                formatter.format(sortedArrivalDates.findFirst().get().getValue()));

    }

    @Test
    public void getOptimalDurationOfTravelling() {
        Stream<Map.Entry<RegiojetSingleSearchResultController, Long>> sortedDurations =
                elementsWithTripDuration.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue());

        System.out.println("The lowest Duration of travelling (in minutes) : " +
                sortedDurations.findFirst().get().getValue());

    }

    @Test
    public void getCheapestPrice() {
        Stream<Map.Entry<RegiojetSingleSearchResultController, Double>> sortedPrices =
                elementsWithPrices.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue());

        System.out.println("The cheapest Ticket Price : " + sortedPrices.findFirst().get().getValue());
    }
}
