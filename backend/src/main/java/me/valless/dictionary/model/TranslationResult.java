package me.valless.dictionary.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TranslationResult {
    private String translation;
    private String sourceLanguageCode;
    private String languageCode;
    private String transcription;
    private String description;
}