package com.mindata.searchservice.lib.shared.domain.exceptions;

import com.mindata.searchservice.lib.shared.domain.DomainError;
import org.springframework.lang.NonNull;

public class IdentifierInvalidException extends DomainError {
    
    public static final String ERROR_CODE = "400";
    
    public IdentifierInvalidException(@NonNull String value) {
        super(ERROR_CODE, "Identifier is not a valid UUID - value : " + value);
    }
}
