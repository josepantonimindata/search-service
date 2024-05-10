package com.mindata.searchservice.shared.domain;

public final class InvalidDateFormatException extends DomainError {
    public InvalidDateFormatException(String errorMessage) {
        super("400", errorMessage);
    }
}
