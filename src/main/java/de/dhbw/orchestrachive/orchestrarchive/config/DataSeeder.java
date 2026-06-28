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
            categoryRepository.save(new Category("Konzertwerk", ""));
            categoryRepository.save(new Category("Prozessionsmarsch", ""));
            categoryRepository.save(new Category("Solo", "Solostimme mit Begleitung"));
            categoryRepository.save(new Category("Konzertmarsch", ""));
            categoryRepository.save(new Category("Klassik", ""));
            categoryRepository.save(new Category("Unterhaltungsmusik", ""));
            categoryRepository.save(new Category("Choral", "Kirchenlied"));
            categoryRepository.save(new Category("Walzer", ""));
            categoryRepository.save(new Category("Marsch", "Festliche Märsche"));
            categoryRepository.save(new Category("Polka", "Böhmische Polkas"));
            categoryRepository.save(new Category("Weihnachten", "Weihnachtsmusik"));

            SheetMusic alteKameraden = sheetMusicRepository.save(new SheetMusic("Alte Kameraden", "Carl Teike", "Unbekannt", 1889, "", DifficultyLevel.MITTELSTUFE));
            SheetMusic boehmischerTraum =  sheetMusicRepository.save(new SheetMusic("Böhmischer Traum", "Karel Vacek", "Unbekannt", 1937, "", DifficultyLevel.UNTERSTUFE));
            SheetMusic radetzky = sheetMusicRepository.save(new SheetMusic("Radetzky Marsch", "Johann Strauss", "Unbekannt", 1848, "", DifficultyLevel.OBERSTUFE));
            SheetMusic fischerinBodensee = sheetMusicRepository.save(new SheetMusic("Fischerin vom Bodensee", "Karl Müller", "Unbekannt", 1905, "", DifficultyLevel.UNTERSTUFE));
            SheetMusic einFreund = sheetMusicRepository.save(new SheetMusic("Ein Freund, ein guter Freund", "Werner Richard Heymann", "Unbekannt", 1930, "", DifficultyLevel.MITTELSTUFE));
            SheetMusic zapfenstreich = sheetMusicRepository.save(new SheetMusic("Großer Zapfenstreich", "Unbekannt", "Unbekannt", 1800, "", DifficultyLevel.OBERSTUFE));
            SheetMusic stilleNacht = sheetMusicRepository.save(new SheetMusic("Stille Nacht", "Franz Xaver Gruber", "Unbekannt", 1818, "", DifficultyLevel.UNTERSTUFE));
            SheetMusic aveMaria = sheetMusicRepository.save(new SheetMusic("Ave Maria", "Franz Schubert", "Unbekannt", 1825, "", DifficultyLevel.MITTELSTUFE));

            // Alte Kameraden
            voicePartRepository.save(new VoicePart("Trompete", 1, "Blechblasinstrumente", 3, alteKameraden));
            voicePartRepository.save(new VoicePart("Trompete", 2, "Blechblasinstrumente", 3, alteKameraden));
            voicePartRepository.save(new VoicePart("Posaune", 1, "Blechblasinstrumente", 2, alteKameraden));
            voicePartRepository.save(new VoicePart("Klarinette", 1, "Holzblasinstrumente", 4, alteKameraden));
            voicePartRepository.save(new VoicePart("Schlagzeug", 1, "Schlagwerk", 1, alteKameraden));

            // Böhmischer Traum
            voicePartRepository.save(new VoicePart("Klarinette", 1, "Holzblasinstrumente", 4, boehmischerTraum));
            voicePartRepository.save(new VoicePart("Klarinette", 2, "Holzblasinstrumente", 4, boehmischerTraum));
            voicePartRepository.save(new VoicePart("Trompete", 1, "Blechblasinstrumente", 3, boehmischerTraum));
            voicePartRepository.save(new VoicePart("Flügelhorn", 1, "Blechblasinstrumente", 3, boehmischerTraum));
            voicePartRepository.save(new VoicePart("Posaune", 1, "Blechblasinstrumente", 2, boehmischerTraum));
            voicePartRepository.save(new VoicePart("Schlagzeug", 1, "Schlagwerk", 1, boehmischerTraum));

            // Radetzky Marsch
            voicePartRepository.save(new VoicePart("Trompete", 1, "Blechblasinstrumente", 4, radetzky));
            voicePartRepository.save(new VoicePart("Trompete", 2, "Blechblasinstrumente", 4, radetzky));
            voicePartRepository.save(new VoicePart("Posaune", 1, "Blechblasinstrumente", 3, radetzky));
            voicePartRepository.save(new VoicePart("Posaune", 2, "Blechblasinstrumente", 3, radetzky));
            voicePartRepository.save(new VoicePart("Klarinette", 1, "Holzblasinstrumente", 4, radetzky));
            voicePartRepository.save(new VoicePart("Schlagzeug", 1, "Schlagwerk", 2, radetzky));

            // Fischerin vom Bodensee
            voicePartRepository.save(new VoicePart("Klarinette", 1, "Holzblasinstrumente", 4, fischerinBodensee));
            voicePartRepository.save(new VoicePart("Trompete", 1, "Blechblasinstrumente", 3, fischerinBodensee));
            voicePartRepository.save(new VoicePart("Posaune", 1, "Blechblasinstrumente", 2, fischerinBodensee));
            voicePartRepository.save(new VoicePart("Schlagzeug", 1, "Schlagwerk", 1, fischerinBodensee));

            // Ein Freund, ein guter Freund
            voicePartRepository.save(new VoicePart("Trompete", 1, "Blechblasinstrumente", 3, einFreund));
            voicePartRepository.save(new VoicePart("Trompete", 2, "Blechblasinstrumente", 3, einFreund));
            voicePartRepository.save(new VoicePart("Saxophon", 1, "Holzblasinstrumente", 2, einFreund));
            voicePartRepository.save(new VoicePart("Schlagzeug", 1, "Schlagwerk", 1, einFreund));

            // Großer Zapfenstreich
            voicePartRepository.save(new VoicePart("Trompete", 1, "Blechblasinstrumente", 4, zapfenstreich));
            voicePartRepository.save(new VoicePart("Trompete", 2, "Blechblasinstrumente", 4, zapfenstreich));
            voicePartRepository.save(new VoicePart("Posaune", 1, "Blechblasinstrumente", 3, zapfenstreich));
            voicePartRepository.save(new VoicePart("Posaune", 2, "Blechblasinstrumente", 3, zapfenstreich));
            voicePartRepository.save(new VoicePart("Schlagzeug", 1, "Schlagwerk", 2, zapfenstreich));

            // Stille Nacht
            voicePartRepository.save(new VoicePart("Klarinette", 1, "Holzblasinstrumente", 3, stilleNacht));
            voicePartRepository.save(new VoicePart("Flügelhorn", 1, "Blechblasinstrumente", 2, stilleNacht));
            voicePartRepository.save(new VoicePart("Posaune", 1, "Blechblasinstrumente", 2, stilleNacht));

            // Ave Maria
            voicePartRepository.save(new VoicePart("Flöte", 1, "Holzblasinstrumente", 2, aveMaria));
            voicePartRepository.save(new VoicePart("Klarinette", 1, "Holzblasinstrumente", 3, aveMaria));
            voicePartRepository.save(new VoicePart("Posaune", 1, "Blechblasinstrumente", 2, aveMaria));
            voicePartRepository.save(new VoicePart("Schlagzeug", 1, "Schlagwerk", 1, aveMaria));
        };
    }
}
