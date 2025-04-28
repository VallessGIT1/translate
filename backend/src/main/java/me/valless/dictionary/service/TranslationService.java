package me.valless.dictionary.service;

import me.valless.dictionary.api.model.translation.TranslationResponse;

public interface TranslationService {

    TranslationResponse getTranslation(
            String text,
            String sourceLanguageCode,
            String targetLanguageCode
    );

    void removeLanguage(String languageCode);
}
