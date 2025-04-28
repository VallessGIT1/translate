package me.valless.dictionary.api.model.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInRequest {
    @NotBlank(message = "Заполните имя пользователя")
    @Size(min = 3, max = 50, message = "Имя пользователя должно быть от 3 до 50 символов")
    private final String username;
    @NotBlank(message = "Заполните пароль")
    @Size(min = 8, message = "Пароль должен быть не менее 8 символов")
    private final String password;
}
