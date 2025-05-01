package me.valless.dictionary.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LanguageDto {
    private final String code;
    private final String display;
}
