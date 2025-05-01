package me.valless.dictionary.service;

import java.util.List;
import me.valless.dictionary.api.model.language.AddLanguageRequest;
import me.valless.dictionary.api.model.language.EditLanguageRequest;
import me.valless.dictionary.api.dto.LanguageDto;
import me.valless.dictionary.api.model.language.RemoveLanguageRequest;

public interface LanguageService {

    LanguageDto getLanguage(String code);

    LanguageDto addLanguage(AddLanguageRequest request);

    LanguageDto editLanguage(EditLanguageRequest request);

    LanguageDto removeLanguage(RemoveLanguageRequest request);

    List<LanguageDto> getLanguages();
}
