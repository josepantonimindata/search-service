package com.mindata.searchservice.app.infrastructure.controllers;

import com.mindata.searchservice.lib.search.application.SearchCommand;
import com.mindata.searchservice.lib.search.domain.exceptions.InvalidSearchArgumentException;
import com.mindata.searchservice.lib.shared.domain.DomainError;
import com.mindata.searchservice.lib.shared.domain.UuidGenerator;
import com.mindata.searchservice.lib.shared.domain.bus.command.CommandBus;
import com.mindata.searchservice.lib.shared.domain.bus.query.QueryBus;
import com.mindata.searchservice.lib.shared.domain.exceptions.DateTimeInvalidException;
import com.mindata.searchservice.lib.shared.domain.exceptions.IdentifierInvalidException;
import com.mindata.searchservice.lib.shared.domain.exceptions.IntInvalidException;
import com.mindata.searchservice.lib.shared.domain.exceptions.NullArgumentException;
import com.mindata.searchservice.lib.shared.infrastructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public final class SearchPostController extends ApiController {
    private final UuidGenerator uuidGenerator;
    
    public SearchPostController(QueryBus queryBus, CommandBus commandBus, UuidGenerator uuidGenerator) {
        super(queryBus, commandBus);
        this.uuidGenerator = uuidGenerator;
    }
    
    @PostMapping("/search")
    public SearchResponse search(@RequestBody SearchRequest request) {
        final String searchId = uuidGenerator.generate();
        
        this.dispatch(new SearchCommand(
            searchId,
            request.hotelId(),
            request.checkIn(),
            request.checkOut(),
            request.ages()
        ));
        
        return new SearchResponse(searchId);
    }
    
    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<>() {{
            put(InvalidSearchArgumentException.class, HttpStatus.UNPROCESSABLE_ENTITY);
            put(NullArgumentException.class, HttpStatus.UNPROCESSABLE_ENTITY);
            put(IntInvalidException.class, HttpStatus.UNPROCESSABLE_ENTITY);
            put(DateTimeInvalidException.class, HttpStatus.UNPROCESSABLE_ENTITY);
            put(IdentifierInvalidException.class, HttpStatus.UNPROCESSABLE_ENTITY);
        }};
    }
}
