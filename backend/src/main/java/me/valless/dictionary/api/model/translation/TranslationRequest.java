package me.valless.dictionary.api.model.translation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TranslationRequest {
    private String text;
    private String source;
    private String target;
}