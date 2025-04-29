package me.valless.dictionary.api.model.language;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditLanguageRequest {
    @Size(min = 1, max = 5, message = "Код языка должен составлять 1-5 символов")
    private final String code;
    private final String display;
}
