package com.mindata.searchservice.lib.shared.domain.objects;

import com.mindata.searchservice.lib.shared.domain.exceptions.NullArgumentException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Objects;

import static com.mindata.searchservice.lib.shared.domain.services.GuardUtils.isNotNull;


public abstract class StringValueObject {
    protected final String value;
    
    /**
     * Creates a new StringValueObject with the given value.
     *
     * @param value the value for the StringValueObject
     * @throws NullArgumentException if the value is null
     */
    public StringValueObject(@NonNull String value) {
        isNotNull(value);
        
        this.value = value;
    }
    
    
    /**
     * Returns the value of the StringValueObject.
     *
     * @return the value of the StringValueObject
     */
    @NonNull
    public String value() {
        return value;
    }
    
    // Generated Methods
    
    /**
     * Checks if the current StringValueObject is equal to the specified object.
     *
     * @param o the object to compare with the current StringValueObject
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StringValueObject that)) {
            return false;
        }
        return Objects.equals(value, that.value);
    }
    
    /**
     * Computes the hash code for the current StringValueObject.
     *
     * @return the hash code value for the current StringValueObject
     */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
