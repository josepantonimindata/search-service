package com.mindata.searchservice.lib.search.domain;

import com.mindata.searchservice.lib.shared.domain.DomainError;

public final class InvalidSearchArgumentException extends DomainError {
    public InvalidSearchArgumentException(String errorMessage) {
        super("401", errorMessage);
    }
}
