package me.valless.dictionary.service;

import me.valless.dictionary.api.dto.HistoryRowDto;
import me.valless.dictionary.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoryService {

    Page<HistoryRowDto> getRows(User user, Pageable pageable);

    void saveRow(User user, String text, String source, String target);
}
