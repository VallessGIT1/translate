package me.valless.dictionary.api.dto;

import java.util.Date;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WordDto {
    private final String id;
    private final Map<String, String> word;
    private final Map<String, String> transcription;
    private final Map<String, String> description;
    private final Date createdAt;
    private final Date updatedAt;
}
