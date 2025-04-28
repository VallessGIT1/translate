package me.valless.dictionary.api.model.auth;

import lombok.Builder;
import lombok.Data;
import me.valless.dictionary.api.dto.UserDto;

@Data
@Builder
public class SignUpResponse {
    private final String token;
    private final UserDto user;
}
