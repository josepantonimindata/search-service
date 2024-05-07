package com.mindata.searchservice.search.infrastructure;

import java.util.List;

public class RequestSearch {
    private String hotelId;
    private String checkIn;
    private String checkOut;
    private List<Integer> ages;

    public RequestSearch(String hotelId, String checkIn, String checkOut, List<Integer> ages) {
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ages = ages;
    }

    public String hotelId() {
        return hotelId;
    }

    public String checkIn() {
        return checkIn;
    }

    public String checkOut() {
        return checkOut;
    }

    public List<Integer> ages() {
        return ages;
    }
}
