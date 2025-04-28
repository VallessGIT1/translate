package me.valless.dictionary.api.model.dictionary;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WordResponse {
    private final String id;
    private final Map<String, String> word;
    private final Map<String, String> transcription;
    private final Map<String, String> description;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
