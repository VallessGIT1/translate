package me.valless.dictionary.api.model.dictionary;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetWordRequest {
    private final String id;
}
