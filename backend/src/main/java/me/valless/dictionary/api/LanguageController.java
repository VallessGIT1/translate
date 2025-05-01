package me.valless.dictionary.api;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.valless.dictionary.api.model.language.AddLanguageRequest;
import me.valless.dictionary.api.model.language.EditLanguageRequest;
import me.valless.dictionary.api.dto.LanguageDto;
import me.valless.dictionary.api.model.language.RemoveLanguageRequest;
import me.valless.dictionary.service.LanguageService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/language")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @GetMapping
    public List<LanguageDto> getLanguages() {
        return languageService.getLanguages();
    }

    @GetMapping("{code}")
    public LanguageDto getLanguage(@PathVariable String code) {
        return languageService.getLanguage(code);
    }

    @PutMapping
    public LanguageDto addLanguage(@Valid @RequestBody AddLanguageRequest request) {
        return languageService.addLanguage(request);
    }

    @PatchMapping
    public LanguageDto editLanguage(@Valid @RequestBody EditLanguageRequest request) {
        return languageService.editLanguage(request);
    }

    @DeleteMapping
    public LanguageDto removeLanguage(@Valid @RequestBody RemoveLanguageRequest request) {
        return languageService.removeLanguage(request);
    }
}