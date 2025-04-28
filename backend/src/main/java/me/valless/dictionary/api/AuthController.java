package me.valless.dictionary.api;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.valless.dictionary.api.mapper.UserMapper;
import me.valless.dictionary.api.model.auth.SignInRequest;
import me.valless.dictionary.api.model.auth.SignInResponse;
import me.valless.dictionary.api.model.auth.SignUpRequest;
import me.valless.dictionary.api.model.auth.SignUpResponse;
import me.valless.dictionary.service.base.BaseUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserMapper userMapper;
    private final BaseUserService userService;

    @PostMapping("signIn")
    public SignInResponse signin(@Valid @RequestBody SignInRequest request) {
        var user = userService.signIn(request.getUsername(), request.getPassword());
        var token = userService.generateToken(request.getUsername(), request.getPassword());
        return SignInResponse.builder()
                .token(token)
                .user(userMapper.map(user))
                .build();
    }

    @PostMapping("signUp")
    public SignUpResponse signup(@Valid @RequestBody SignUpRequest request) {
        var user = userService.signUp(request);
        var token = userService.generateToken(request.getUsername(), request.getPassword());
        return SignUpResponse.builder()
                .token(token)
                .user(userMapper.map(user))
                .build();
    }
}
