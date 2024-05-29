package com.mindata.searchservice.lib.search.domain;


import com.mindata.searchservice.lib.search.domain.exceptions.InvalidSearchArgumentException;
import com.mindata.searchservice.lib.shared.domain.objects.StringValueObject;

public final class HotelId extends StringValueObject {
    public HotelId(String value) {
        super(value);
        isNotEmpty(value);
    }
    
    private void isNotEmpty(String value) {
        if (value.isEmpty()) {
            throw new InvalidSearchArgumentException("HotelId can not be null");
        }
    }
}
