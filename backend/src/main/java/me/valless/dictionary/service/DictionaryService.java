package me.valless.dictionary.service;

import java.util.List;
import me.valless.dictionary.api.model.dictionary.AddWordRequest;
import me.valless.dictionary.api.model.dictionary.EditWordRequest;
import me.valless.dictionary.api.model.dictionary.GetWordRequest;
import me.valless.dictionary.api.model.dictionary.RemoveWordRequest;
import me.valless.dictionary.api.model.dictionary.WordResponse;

public interface DictionaryService {

    List<WordResponse> getWords();

    WordResponse addWord(AddWordRequest request);

    WordResponse editWord(EditWordRequest request);

    WordResponse removeWord(RemoveWordRequest request);

    WordResponse getWord(GetWordRequest request);
}
