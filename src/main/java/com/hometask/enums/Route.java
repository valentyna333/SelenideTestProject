package com.hometask.enums;

/**
 * Possible values of routes with name and id (for web service request)
 */
public enum Route {

    OSTRAVA("Ostrava", 10202000),
    BRNO("Brno", 10202002);

    private final String routeName;
    private final int routeId;

    Route(String routeName, int routeId) {
        this.routeName = routeName;
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public int getRouteId() {
        return routeId;
    }
}
