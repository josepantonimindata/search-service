package com.mindata.searchservice.search.domain;

import com.mindata.searchservice.shared.domain.DomainError;

public final class InvalidSearchArgumentException extends DomainError {
    public InvalidSearchArgumentException(String errorMessage) {
        super("401", errorMessage);
    }
}
