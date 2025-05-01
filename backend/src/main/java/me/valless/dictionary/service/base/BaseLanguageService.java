package me.valless.dictionary.service.base;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.valless.dictionary.api.model.language.AddLanguageRequest;
import me.valless.dictionary.api.model.language.EditLanguageRequest;
import me.valless.dictionary.api.dto.LanguageDto;
import me.valless.dictionary.api.model.language.RemoveLanguageRequest;
import me.valless.dictionary.exception.LanguageNotFoundException;
import me.valless.dictionary.mapper.LanguageMapper;
import me.valless.dictionary.repository.database.LanguageRepository;
import me.valless.dictionary.service.LanguageService;
import me.valless.dictionary.service.TranslationService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseLanguageService implements LanguageService {

    private final TranslationService translationService;
    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    @Override
    public List<LanguageDto> getLanguages() {
        return languageRepository.findAll()
                .stream()
                .map(languageMapper::map)
                .toList();
    }

    @Override
    public LanguageDto getLanguage(String code) {
        return languageRepository.findByLanguageCode(code)
                .map(languageMapper::map)
                .orElseThrow(LanguageNotFoundException::new);
    }

    @Override
    public LanguageDto addLanguage(AddLanguageRequest request) {
        var language = languageMapper.map(request);
        language = languageRepository.save(language);
        return languageMapper.map(language);
    }

    @Override
    public LanguageDto editLanguage(EditLanguageRequest request) {
        var language = languageMapper.map(request);
        language = languageRepository.save(language);
        return languageMapper.map(language);
    }

    @Override
    public LanguageDto removeLanguage(RemoveLanguageRequest request) {
        var dictionaryEntry = languageMapper.map(request);
        languageRepository.delete(dictionaryEntry);
        translationService.removeLanguage(request.getCode());
        return languageMapper.map(dictionaryEntry);
    }
}
