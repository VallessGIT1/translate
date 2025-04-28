package me.valless.dictionary.api.model.dictionary;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

//TODO: VALIDATION!!!

@Data
@Builder
public class AddWordRequest {
    private final Map<String, String> word;
    private final Map<String, String> transcription;
    private final Map<String, String> description;
}
