package me.valless.dictionary.api.model.language;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetLanguageRequest {
    private final String code;
}
