package me.valless.dictionary.api;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.valless.dictionary.api.model.dictionary.AddWordRequest;
import me.valless.dictionary.api.model.dictionary.EditWordRequest;
import me.valless.dictionary.api.model.dictionary.RemoveWordRequest;
import me.valless.dictionary.api.dto.WordDto;
import me.valless.dictionary.service.DictionaryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dictionary")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping
    public List<WordDto> getWords() {
        return dictionaryService.getWords();
    }

    @GetMapping("{wordId}")
    public WordDto getWord(@PathVariable String wordId) {
        return dictionaryService.getWord(wordId);
    }

    @PutMapping
    public WordDto addWord(@Valid @RequestBody AddWordRequest request) {
        return dictionaryService.addWord(request);
    }

    @PatchMapping
    public WordDto editWord(@Valid @RequestBody EditWordRequest request) {
        return dictionaryService.editWord(request);
    }

    @DeleteMapping
    public WordDto removeWord(@Valid @RequestBody RemoveWordRequest request) {
        return dictionaryService.removeWord(request);
    }
}
