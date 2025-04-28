package me.valless.dictionary.api.dto;

import lombok.Builder;
import lombok.Getter;
import me.valless.dictionary.model.Role;

@Getter
@Builder
public class UserDto {
    private final Long id;
    private final String username;
    private final String name;
    private final Role role;
}
