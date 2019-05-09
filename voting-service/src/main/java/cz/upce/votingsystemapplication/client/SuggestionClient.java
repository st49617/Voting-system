package cz.upce.votingsystemapplication.client;

import cz.upce.votingsystemapplication.dto.SuggestionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "suggestion-service", fallback = SuggestionClientFallback.class)
public interface SuggestionClient {

    @RequestMapping(method = RequestMethod.GET, value = "get/{id}")
    SuggestionDto findById(@PathVariable("id") Long suggestionId);
}