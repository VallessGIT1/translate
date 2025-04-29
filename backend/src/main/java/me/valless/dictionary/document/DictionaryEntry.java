package me.valless.dictionary.document;

import static me.valless.dictionary.config.Constants.ELASTICSEARCH_INDEX;
import static org.springframework.data.elasticsearch.annotations.FieldType.Date;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

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
    @Field(name = "created_at", type = Date)
    private Date createdAt;
    @Field(name = "updated_at", type = Date)
    private Date updatedAt;

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
