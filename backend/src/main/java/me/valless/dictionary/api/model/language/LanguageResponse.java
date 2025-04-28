package me.valless.dictionary.api.model.language;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LanguageResponse {
    private final String code;
    private final String display;
}
