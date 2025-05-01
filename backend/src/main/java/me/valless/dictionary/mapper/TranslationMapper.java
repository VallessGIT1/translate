package me.valless.dictionary.mapper;

import me.valless.dictionary.api.model.dictionary.AddWordRequest;
import me.valless.dictionary.api.model.dictionary.EditWordRequest;
import me.valless.dictionary.api.model.dictionary.RemoveWordRequest;
import me.valless.dictionary.api.dto.WordDto;
import me.valless.dictionary.document.DictionaryEntry;
import me.valless.dictionary.model.TranslationResult;
import org.springframework.stereotype.Component;

@Component
public class TranslationMapper {

    public DictionaryEntry map(AddWordRequest request) {
        return DictionaryEntry.builder()
                .word(request.getWord())
                .transcription(request.getTranscription())
                .description(request.getDescription())
                .build();
    }

    public DictionaryEntry map(EditWordRequest request) {
        return DictionaryEntry.builder()
                .id(request.getId())
                .word(request.getWord())
                .transcription(request.getTranscription())
                .description(request.getDescription())
                .build();
    }

    public DictionaryEntry map(RemoveWordRequest request) {
        return DictionaryEntry.builder()
                .id(request.getId())
                .build();
    }

    public WordDto map(DictionaryEntry dictionaryEntry) {
        return WordDto.builder()
                .id(dictionaryEntry.getId())
                .word(dictionaryEntry.getWord())
                .transcription(dictionaryEntry.getTranscription())
                .description(dictionaryEntry.getDescription())
                .createdAt(dictionaryEntry.getCreatedAt())
                .updatedAt(dictionaryEntry.getUpdatedAt())
                .build();
    }

    public TranslationResult map(String sourceLanguageCode, String targetLanguageCode,
            DictionaryEntry searchResult) {
        return TranslationResult.builder()
                .sourceLanguageCode(sourceLanguageCode)
                .languageCode(targetLanguageCode)
                .translation(searchResult.getWordByLanguage(targetLanguageCode))
                .transcription(searchResult.getTranscriptionByLanguage(targetLanguageCode))
                .description(searchResult.getDescriptionByLanguage(targetLanguageCode))
                .build();
    }
}
