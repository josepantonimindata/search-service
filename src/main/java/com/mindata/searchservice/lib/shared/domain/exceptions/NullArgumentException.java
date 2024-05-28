package com.mindata.searchservice.lib.shared.domain.exceptions;


import com.mindata.searchservice.lib.shared.domain.DomainError;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class NullArgumentException extends DomainError {
    public static final String ERROR_CODE = "422";
    
    private final Optional<String> field;
    
    public NullArgumentException() {
        super(ERROR_CODE, "The argument passed to function as 'null'");
        this.field = Optional.empty();
    }
    
    public NullArgumentException(@NonNull String field) {
        super(ERROR_CODE, "The argument passed to function as 'null' for field: " + field);
        this.field = Optional.of(field);
    }
    
    public NullArgumentException(@NonNull String message, @Nullable String field) {
        super(ERROR_CODE, message);
        this.field = Optional.ofNullable(field);
    }
    
    public Optional<String> field() {
        return this.field;
    }
}
