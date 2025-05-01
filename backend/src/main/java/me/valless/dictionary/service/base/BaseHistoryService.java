package me.valless.dictionary.service.base;

import java.util.Date;
import lombok.RequiredArgsConstructor;
import me.valless.dictionary.api.dto.HistoryRowDto;
import me.valless.dictionary.entity.HistoryRow;
import me.valless.dictionary.entity.User;
import me.valless.dictionary.mapper.HistoryMapper;
import me.valless.dictionary.repository.database.HistoryRepository;
import me.valless.dictionary.service.HistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseHistoryService implements HistoryService {

    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;

    @Override
    public Page<HistoryRowDto> getRows(User user, Pageable pageable) {
        return historyRepository.findAllByUser(user, pageable)
                .map(historyMapper::map);
    }

    @Override
    public void saveRow(User user, String text, String source, String target) {
        var row = HistoryRow.builder()
                .user(user)
                .text(text)
                .sourceCode(source)
                .targetCode(target)
                .date(new Date())
                .build();
        historyRepository.save(row);
    }
}
