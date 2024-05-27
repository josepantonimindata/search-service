package com.mindata.searchservice.lib.search.domain;

import com.mindata.searchservice.lib.shared.domain.StringValueObject;

public final class HotelId extends StringValueObject {
    public HotelId(String value) {
        super(value);
        ensureValueIsDefined(value);
    }

    private void ensureValueIsDefined(String value) {
        if (value == null || value.isEmpty()) {
            throw new InvalidSearchArgumentException("HotellId can not be null");
        }
    }
}
