package me.valless.dictionary.api.model.translation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TranslationRequest {
    @NotBlank(message = "Введите текст для перевода")
    private String text;
    @Size(min = 1, max = 5, message = "Код языка должен составлять 1-5 символов")
    private String source;
    @Size(min = 1, max = 5, message = "Код языка должен составлять 1-5 символов")
    private String target;
    private boolean save;
}