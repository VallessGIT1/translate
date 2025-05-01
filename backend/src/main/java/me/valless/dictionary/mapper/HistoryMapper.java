package me.valless.dictionary.mapper;

import me.valless.dictionary.api.dto.HistoryRowDto;
import me.valless.dictionary.api.model.language.AddLanguageRequest;
import me.valless.dictionary.entity.HistoryRow;
import me.valless.dictionary.entity.Language;
import org.springframework.stereotype.Component;

@Component
public class HistoryMapper {

    public HistoryRowDto map(HistoryRow row) {
        return HistoryRowDto.builder()
                .id(row.getId())
                .text(row.getText())
                .sourceCode(row.getSourceCode())
                .targetCode(row.getTargetCode())
                .date(row.getDate())
                .build();
    }
}
