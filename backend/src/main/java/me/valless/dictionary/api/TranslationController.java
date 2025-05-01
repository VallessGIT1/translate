package me.valless.dictionary.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.valless.dictionary.api.model.translation.TranslationRequest;
import me.valless.dictionary.api.model.translation.TranslationResponse;
import me.valless.dictionary.service.HistoryService;
import me.valless.dictionary.service.TranslationService;
import me.valless.dictionary.service.base.BaseUserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/translation")
@RequiredArgsConstructor
public class TranslationController {

    private final BaseUserService userService;
    private final HistoryService historyService;
    private final TranslationService translationService;

    @PostMapping
    public TranslationResponse getTranslation(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody TranslationRequest request
    ) {
        var response = translationService.getTranslation(
                request.getText(),
                request.getSource(),
                request.getTarget()
        );
        if (request.isSave()) {
            var user = userService.getUser(userDetails);
            historyService.saveRow(
                    user,
                    request.getText(),
                    response.getSource(),
                    request.getTarget()
            );
        }
        return response;
    }
}