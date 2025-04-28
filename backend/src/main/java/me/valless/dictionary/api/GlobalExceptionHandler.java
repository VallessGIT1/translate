package me.valless.dictionary.api;

import me.valless.dictionary.api.model.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException exception) {
        var response = ErrorResponse.builder()
                .message(exception.getReason())
                .build();
        return new ResponseEntity<>(response, exception.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {
        var response = ErrorResponse.builder()
                .message(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .build();
        return new ResponseEntity<>(response, exception.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception exception) {
        var response = ErrorResponse.builder()
                .message("Внутренняя ошибка сервера: " + exception.getClass().getSimpleName() + " -> " + exception.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
