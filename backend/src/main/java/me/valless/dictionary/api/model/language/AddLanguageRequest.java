package me.valless.dictionary.api.model.language;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddLanguageRequest {
    private final String display;
    private final String code;
}
