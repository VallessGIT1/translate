package me.valless.dictionary.service.base;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import me.valless.dictionary.api.model.translation.TranslationResponse;
import me.valless.dictionary.service.ElasticsearchService;
import me.valless.dictionary.service.LanguageDetectionService;
import me.valless.dictionary.service.TranslationService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseTranslationService implements TranslationService {

    private final LanguageDetectionService languageDetectionService;
    private final ElasticsearchService elasticsearchService;

    public TranslationResponse getTranslation(String text, String sourceLanguageCode, String targetLanguageCode) {

        if (Objects.isNull(sourceLanguageCode)) {
            sourceLanguageCode = languageDetectionService.detectLanguage(text);
        }

        var result = elasticsearchService.searchTranslation(text, sourceLanguageCode, targetLanguageCode);

        return TranslationResponse.builder()
                .source(result.getSourceLanguageCode())
                .languageCode(result.getLanguageCode())
                .translation(result.getTranslation())
                .transcription(result.getTranscription())
                .description(result.getDescription())
                .build();
    }

    @Override
    public void removeLanguage(String languageCode) {
        elasticsearchService.removeLanguage(languageCode);
    }
}
