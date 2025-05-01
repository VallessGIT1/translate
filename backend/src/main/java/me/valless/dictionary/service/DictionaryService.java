package me.valless.dictionary.service;

import java.util.List;
import me.valless.dictionary.api.model.dictionary.AddWordRequest;
import me.valless.dictionary.api.model.dictionary.EditWordRequest;
import me.valless.dictionary.api.model.dictionary.RemoveWordRequest;
import me.valless.dictionary.api.dto.WordDto;

public interface DictionaryService {

    List<WordDto> getWords();

    WordDto addWord(AddWordRequest request);

    WordDto editWord(EditWordRequest request);

    WordDto removeWord(RemoveWordRequest request);

    WordDto getWord(String wordId);
}
