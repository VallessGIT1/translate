package me.valless.dictionary.api;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.valless.dictionary.api.model.dictionary.AddWordRequest;
import me.valless.dictionary.api.model.dictionary.EditWordRequest;
import me.valless.dictionary.api.model.dictionary.GetWordRequest;
import me.valless.dictionary.api.model.dictionary.RemoveWordRequest;
import me.valless.dictionary.api.model.dictionary.WordResponse;
import me.valless.dictionary.service.DictionaryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dictionary")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping("list")
    public List<WordResponse> getWords() {
        return dictionaryService.getWords();
    }

    @GetMapping
    public WordResponse getWord(@Valid @RequestBody GetWordRequest request) {
        return dictionaryService.getWord(request);
    }

    @PutMapping
    public WordResponse addWord(@Valid @RequestBody AddWordRequest request) {
        return dictionaryService.addWord(request);
    }

    @PatchMapping
    public WordResponse editWord(@Valid @RequestBody EditWordRequest request) {
        return dictionaryService.editWord(request);
    }

    @DeleteMapping
    public WordResponse removeWord(@Valid @RequestBody RemoveWordRequest request) {
        return dictionaryService.removeWord(request);
    }
}
