package cz.upce.votingsystemapplication.client;

import cz.upce.votingsystemapplication.dto.SuggestionDto;
import org.springframework.stereotype.Component;

@Component
public class SuggestionClientFallback implements SuggestionClient {

    @Override
    public SuggestionDto findById(Long suggestionId) {
        return null;
    }
}
