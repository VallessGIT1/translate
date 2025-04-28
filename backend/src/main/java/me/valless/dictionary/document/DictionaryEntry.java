package me.valless.dictionary.document;

import static me.valless.dictionary.config.Constants.ELASTICSEARCH_INDEX;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = ELASTICSEARCH_INDEX)
public class DictionaryEntry {

    private String id;
    private Map<String, String> word;
    private Map<String, String> transcription;
    private Map<String, String> description;
    private String createdAt;
    private String updatedAt;

    public String getWordByLanguage(String languageCode) {
        return word.get(languageCode);
    }

    public String getTranscriptionByLanguage(String languageCode) {
        return transcription.get(languageCode);
    }

    public String getDescriptionByLanguage(String languageCode) {
        return description.get(languageCode);
    }
}
