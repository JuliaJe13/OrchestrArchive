package de.dhbw.orchestrachive.orchestrarchive.config;

import de.dhbw.orchestrachive.orchestrarchive.model.Category;
import de.dhbw.orchestrachive.orchestrarchive.model.DifficultyLevel;
import de.dhbw.orchestrachive.orchestrarchive.model.SheetMusic;
import de.dhbw.orchestrachive.orchestrarchive.model.VoicePart;
import de.dhbw.orchestrachive.orchestrarchive.model.repository.CategoryRepository;
import de.dhbw.orchestrachive.orchestrarchive.model.repository.SheetMusicRepository;
import de.dhbw.orchestrachive.orchestrarchive.model.repository.VoicePartRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seed(CategoryRepository categoryRepository,
                           SheetMusicRepository sheetMusicRepository,
                           VoicePartRepository voicePartRepository) {
        return args -> {
            if (categoryRepository.count() > 0) {
                return;
            }
            categoryRepository.save(new Category("Marsch", "Festliche Märsche"));
            categoryRepository.save(new Category("Polka", "Böhmische Polkas"));
            categoryRepository.save(new Category("Weihnachten", "Weihnachtsmusik"));
            SheetMusic alteKameraden = sheetMusicRepository.save(new SheetMusic("Alte Kameraden", "Carl Teike", "traditional", 1889, "Unknown", DifficultyLevel.MITTELSTUFE));
            SheetMusic boehmischerTraum =  sheetMusicRepository.save(new SheetMusic("Böhmischer Traum", "Karel Vacek", "traditional", 1937, "Unknown", DifficultyLevel.UNTERSTUFE));
            SheetMusic radetzky = sheetMusicRepository.save(new SheetMusic("Radetzky Marsch", "Johann Strauss", "traditional", 1848, "Unknown", DifficultyLevel.OBERSTUFE));
            voicePartRepository.save(new VoicePart("Trompete", 1, "Blechblasinstrumente", 3, alteKameraden));
            voicePartRepository.save(new VoicePart("Trompete", 2, "Blechblasinstrumente", 3, alteKameraden));
            voicePartRepository.save(new VoicePart("Posaune", 1, "Blechblasinstrumente", 2, alteKameraden));
            voicePartRepository.save(new VoicePart("Klarinette", 1, "Holzblasinstrumente", 4, alteKameraden));
            voicePartRepository.save(new VoicePart("Schlagzeug", 1, "Schlagwerk", 1, alteKameraden));
        };
    }
}
