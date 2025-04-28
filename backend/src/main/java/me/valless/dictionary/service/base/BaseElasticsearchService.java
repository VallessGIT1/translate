package me.valless.dictionary.service.base;

import static java.util.Collections.singletonMap;
import static me.valless.dictionary.config.Constants.ELASTICSEARCH_INDEX;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import me.valless.dictionary.exception.TranslationNotFoundException;
import me.valless.dictionary.mapper.TranslationMapper;
import me.valless.dictionary.model.TranslationResult;
import me.valless.dictionary.repository.elasticsearch.TranslationRepository;
import me.valless.dictionary.service.ElasticsearchService;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.ScriptType;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseElasticsearchService implements ElasticsearchService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final TranslationRepository translationRepository;
    private final TranslationMapper translationMapper;

    @Override
    public TranslationResult searchTranslation(String text, String sourceLanguageCode, String targetLanguageCode) {

        var searchResult = translationRepository.findBySourceLanguageCodeAndWord(sourceLanguageCode, text);

        if (Objects.isNull(searchResult)) {
            throw new TranslationNotFoundException();
        }

        return translationMapper.map(sourceLanguageCode, targetLanguageCode, searchResult);
    }

    @Override
    public void removeLanguage(String languageCode) {

        var script = String.format(
                "ctx._source.remove('%s.word'); ctx._source.remove('%s.transcription'); ctx._source.remove('%s.description');",
                languageCode, languageCode, languageCode
        );

        var request = UpdateQuery.builder("remove_language")
                .withScriptType(ScriptType.INLINE)
                .withParams(singletonMap("languageCode", languageCode))
                .withLang("painless")
                .withScript(script)
                .build();

        elasticsearchOperations.updateByQuery(request, IndexCoordinates.of(ELASTICSEARCH_INDEX));
    }
}
