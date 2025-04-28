package me.valless.dictionary.repository.elasticsearch;

import me.valless.dictionary.document.DictionaryEntry;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationRepository extends ElasticsearchRepository<DictionaryEntry, String> {

    @Query("""
            {"bool": {"must": [{"match": {"word.?0": "?1"}}]}}
            """)
    DictionaryEntry findBySourceLanguageCodeAndWord(String sourceLanguageCode, String text);
}