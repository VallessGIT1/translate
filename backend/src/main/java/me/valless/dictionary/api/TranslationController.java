package me.valless.dictionary.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.valless.dictionary.api.model.translation.TranslationRequest;
import me.valless.dictionary.api.model.translation.TranslationResponse;
import me.valless.dictionary.service.base.BaseTranslationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/translation")
@RequiredArgsConstructor
public class TranslationController {

    private final BaseTranslationService translationService;

    @PostMapping
    public TranslationResponse getTranslation(@Valid @RequestBody TranslationRequest request) {
        return translationService.getTranslation(
                request.getText(),
                request.getSource(),
                request.getTarget()
        );
    }
}