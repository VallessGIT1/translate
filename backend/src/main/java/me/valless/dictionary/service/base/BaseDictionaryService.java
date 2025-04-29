package me.valless.dictionary.service.base;

import java.util.List;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import me.valless.dictionary.api.model.dictionary.AddWordRequest;
import me.valless.dictionary.api.model.dictionary.EditWordRequest;
import me.valless.dictionary.api.model.dictionary.GetWordRequest;
import me.valless.dictionary.api.model.dictionary.RemoveWordRequest;
import me.valless.dictionary.api.model.dictionary.WordResponse;
import me.valless.dictionary.exception.WordNotFoundException;
import me.valless.dictionary.mapper.TranslationMapper;
import me.valless.dictionary.repository.elasticsearch.TranslationRepository;
import me.valless.dictionary.service.DictionaryService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseDictionaryService implements DictionaryService {

    private final TranslationRepository translationRepository;
    private final TranslationMapper translationMapper;

    @Override
    public List<WordResponse> getWords() {
        var words = translationRepository.findAll();
        return StreamSupport.stream(words.spliterator(), false)
                .map(translationMapper::map)
                .toList();
    }

    @Override
    public WordResponse getWord(GetWordRequest request) {
        var dictionaryEntry = translationRepository.findById(request.getId())
                .orElseThrow(WordNotFoundException::new);
        return translationMapper.map(dictionaryEntry);
    }

    @Override
    public WordResponse addWord(AddWordRequest request) {
        var dictionaryEntry = translationMapper.map(request);
        dictionaryEntry = translationRepository.save(dictionaryEntry);
        return translationMapper.map(dictionaryEntry);
    }

    @Override
    public WordResponse editWord(EditWordRequest request) {
        var dictionaryEntry = translationMapper.map(request);
        dictionaryEntry = translationRepository.save(dictionaryEntry);
        return translationMapper.map(dictionaryEntry);
    }

    @Override
    public WordResponse removeWord(RemoveWordRequest request) {
        var dictionaryEntry = translationMapper.map(request);
        translationRepository.delete(dictionaryEntry);
        return translationMapper.map(dictionaryEntry);
    }
}
