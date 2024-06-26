package com.mindata.searchservice.lib.shared.infrastructure;

import com.mindata.searchservice.lib.shared.domain.UuidGenerator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class JavaUuidGenerator implements UuidGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}

