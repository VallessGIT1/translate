package me.valless.dictionary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LanguageNotRecognizedException extends ResponseStatusException {

    public LanguageNotRecognizedException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY, "Язык не распознан");
    }
}
