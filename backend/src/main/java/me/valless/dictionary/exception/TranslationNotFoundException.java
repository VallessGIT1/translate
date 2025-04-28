package me.valless.dictionary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TranslationNotFoundException extends ResponseStatusException {

    public TranslationNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Перевод не найден");
    }
}
