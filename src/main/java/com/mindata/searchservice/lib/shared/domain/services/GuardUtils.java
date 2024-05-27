package com.mindata.searchservice.lib.shared.domain.services;

import com.mindata.searchservice.lib.shared.domain.exceptions.NullArgumentException;
import org.springframework.lang.Nullable;

public class GuardUtils {
    
    public static void isNotNull(@Nullable Object value) throws NullArgumentException {
        if (value == null) {
            throw new NullArgumentException();
        }
    }
}
