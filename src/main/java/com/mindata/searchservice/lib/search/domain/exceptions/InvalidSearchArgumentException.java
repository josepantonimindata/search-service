package com.mindata.searchservice.lib.search.domain.exceptions;

import com.mindata.searchservice.lib.shared.domain.DomainError;

public final class InvalidSearchArgumentException extends DomainError {
    public InvalidSearchArgumentException(String errorMessage) {
        super("422", errorMessage);
    }
}
