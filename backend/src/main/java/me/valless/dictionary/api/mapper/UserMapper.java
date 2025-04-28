package me.valless.dictionary.api.mapper;

import me.valless.dictionary.api.dto.UserDto;
import me.valless.dictionary.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto map(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
}
