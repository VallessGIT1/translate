package me.valless.dictionary.api.model.translation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TranslationResponse {
    private String translation;
    private String source;
    private String languageCode;
    private String transcription;
    private String description;
}