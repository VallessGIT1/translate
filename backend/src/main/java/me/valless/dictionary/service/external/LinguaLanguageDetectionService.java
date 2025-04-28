package me.valless.dictionary.service.external;

import com.github.pemistahl.lingua.api.Language;
import com.github.pemistahl.lingua.api.LanguageDetector;
import com.github.pemistahl.lingua.api.LanguageDetectorBuilder;
import lombok.RequiredArgsConstructor;
import me.valless.dictionary.exception.LanguageNotRecognizedException;
import me.valless.dictionary.service.LanguageDetectionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinguaLanguageDetectionService implements LanguageDetectionService {

    private static final LanguageDetector detector = LanguageDetectorBuilder.fromLanguages(
            Language.ENGLISH,
            Language.RUSSIAN,
            Language.TATAR
    ).build();

    public String detectLanguage(String text) {
        var result = detector.detectLanguageOf(text);

        if (result == Language.UNKNOWN) {
            throw new LanguageNotRecognizedException();
        }

        return result.getIsoCode639_1().name().toLowerCase();
    }
}
