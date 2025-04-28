package me.valless.dictionary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LanguageNotFoundException extends ResponseStatusException {

    public LanguageNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Язык не найден");
    }
}
