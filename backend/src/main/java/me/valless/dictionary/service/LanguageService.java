package me.valless.dictionary.service;

import java.util.List;
import me.valless.dictionary.api.model.language.AddLanguageRequest;
import me.valless.dictionary.api.model.language.EditLanguageRequest;
import me.valless.dictionary.api.model.language.GetLanguageRequest;
import me.valless.dictionary.api.model.language.LanguageResponse;
import me.valless.dictionary.api.model.language.RemoveLanguageRequest;

public interface LanguageService {

    LanguageResponse getLanguage(GetLanguageRequest request);

    LanguageResponse addLanguage(AddLanguageRequest request);

    LanguageResponse editLanguage(EditLanguageRequest request);

    LanguageResponse removeLanguage(RemoveLanguageRequest request);

    List<LanguageResponse> getLanguages();
}
