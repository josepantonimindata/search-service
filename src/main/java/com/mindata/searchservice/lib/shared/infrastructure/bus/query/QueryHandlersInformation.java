package com.mindata.searchservice.lib.shared.infrastructure.bus.query;

import com.mindata.searchservice.lib.shared.domain.bus.query.Query;
import com.mindata.searchservice.lib.shared.domain.bus.query.QueryHandler;
import com.mindata.searchservice.lib.shared.domain.bus.query.QueryNotRegisteredError;
import org.reflections.Reflections;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Set;

@SuppressWarnings("rawtypes")
@Service
public final class QueryHandlersInformation {
    HashMap<Class<? extends Query>, Class<? extends QueryHandler>> indexedQueryHandlers;
    
    public QueryHandlersInformation() {
        Reflections reflections = new Reflections("com.mindata.searchservice");
        Set<Class<? extends QueryHandler>> classes = reflections.getSubTypesOf(QueryHandler.class);
        
        indexedQueryHandlers = formatHandlers(classes);
    }
    
    public Class<? extends QueryHandler> search(Class<? extends Query> queryClass) throws QueryNotRegisteredError {
        Class<? extends QueryHandler> queryHandlerClass = indexedQueryHandlers.get(queryClass);
        
        if (null == queryHandlerClass) {
            throw new QueryNotRegisteredError(queryClass);
        }
        
        return queryHandlerClass;
    }
    
    private HashMap<Class<? extends Query>, Class<? extends QueryHandler>> formatHandlers(
        Set<Class<? extends QueryHandler>> queryHandlers
    ) {
        HashMap<Class<? extends Query>, Class<? extends QueryHandler>> handlers = new HashMap<>();
        
        for (Class<? extends QueryHandler> handler : queryHandlers) {
            ParameterizedType paramType = (ParameterizedType) handler.getGenericInterfaces()[0];
            Class<? extends Query> queryClass = (Class<? extends Query>) paramType.getActualTypeArguments()[0];
            
            handlers.put(queryClass, handler);
        }
        
        return handlers;
    }
}
