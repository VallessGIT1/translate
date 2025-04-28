package me.valless.dictionary.repository.database;

import java.util.Optional;
import me.valless.dictionary.entity.Language;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends ListCrudRepository<Language, String> {

    Optional<Language> findByLanguageCode(String code);
}
