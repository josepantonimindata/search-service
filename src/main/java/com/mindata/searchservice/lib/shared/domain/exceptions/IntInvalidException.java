package com.mindata.searchservice.lib.shared.domain.exceptions;

import com.mindata.searchservice.lib.shared.domain.DomainError;
import org.springframework.lang.NonNull;

public class IntInvalidException extends DomainError {
    
    public static final String ERROR_CODE = "400";
    
    public IntInvalidException(@NonNull String value) {
        super(ERROR_CODE, "Identifier value is not a valid Natural Number - value : " + value);
    }
}
