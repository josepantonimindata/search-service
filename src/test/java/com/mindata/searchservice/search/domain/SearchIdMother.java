package com.mindata.searchservice.search.domain;

import com.mindata.searchservice.lib.search.domain.SearchId;
import com.mindata.searchservice.shared.domain.UuidMother;

public final class SearchIdMother {
    public static SearchId create(String value) {
        return new SearchId(value);
    }

    public static SearchId random() {
        return new SearchId(UuidMother.random());
    }
}
