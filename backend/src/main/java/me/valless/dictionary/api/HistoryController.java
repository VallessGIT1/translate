package me.valless.dictionary.api;


import lombok.RequiredArgsConstructor;
import me.valless.dictionary.api.dto.HistoryRowDto;
import me.valless.dictionary.service.HistoryService;
import me.valless.dictionary.service.base.BaseUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {

    private final BaseUserService userService;
    private final HistoryService historyService;

    @GetMapping
    public Page<HistoryRowDto> getHistory(@AuthenticationPrincipal UserDetails userDetails, Pageable pageable) {
        var user = userService.getUser(userDetails);
        return historyService.getRows(user, pageable);
    }
}