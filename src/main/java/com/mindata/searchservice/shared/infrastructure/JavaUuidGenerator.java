package com.mindata.searchservice.shared.infrastructure;

import com.mindata.searchservice.shared.domain.Service;
import com.mindata.searchservice.shared.domain.UuidGenerator;

import java.util.UUID;

@Service
public final class JavaUuidGenerator implements UuidGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
