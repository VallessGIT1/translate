package me.valless.dictionary.service;

import me.valless.dictionary.model.TranslationResult;

public interface ElasticsearchService {

    TranslationResult searchTranslation(String text, String sourceLanguageCode, String targetLanguageCode);

    void removeLanguage(String languageCode);
}
