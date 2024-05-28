package com.mindata.searchservice.lib.shared.domain.exceptions;

import com.mindata.searchservice.lib.shared.domain.DomainError;
import org.springframework.lang.NonNull;

public class DateTimeInvalidException extends DomainError {
    
    public static final String ERROR_CODE = "422";
    
    public DateTimeInvalidException(@NonNull String value) {
        super(ERROR_CODE, "Value is not a valid DateTime - value : " + value);
    }
}
