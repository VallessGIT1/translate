package me.valless.dictionary.mapper;

import me.valless.dictionary.api.model.language.AddLanguageRequest;
import me.valless.dictionary.api.model.language.EditLanguageRequest;
import me.valless.dictionary.api.model.language.LanguageResponse;
import me.valless.dictionary.api.model.language.RemoveLanguageRequest;
import me.valless.dictionary.entity.Language;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper {

    public Language map(AddLanguageRequest request) {
        return Language.builder()
                .languageCode(request.getCode())
                .display(request.getDisplay())
                .build();
    }

    public LanguageResponse map(Language language) {
        return LanguageResponse.builder()
                .code(language.getLanguageCode())
                .display(language.getDisplay())
                .build();
    }

    public Language map(EditLanguageRequest request) {
        return Language.builder()
              .languageCode(request.getCode())
              .display(request.getDisplay())
              .build();
    }

    public Language map(RemoveLanguageRequest request) {
        return Language.builder()
              .languageCode(request.getCode())
              .build();
    }
}
