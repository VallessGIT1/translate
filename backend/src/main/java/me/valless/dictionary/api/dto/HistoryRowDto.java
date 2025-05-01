package me.valless.dictionary.api.dto;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HistoryRowDto {
    private final Long id;
    private final String text;
    private final String sourceCode;
    private final String targetCode;
    private final Date date;
}
