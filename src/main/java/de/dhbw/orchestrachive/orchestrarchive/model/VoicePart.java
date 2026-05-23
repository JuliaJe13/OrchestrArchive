package de.dhbw.orchestrachive.orchestrarchive.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class VoicePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String instrument;
    private int partNumber;
    private String instrumentGroup;
    private int sheetCount;

    @ManyToOne
    @JoinColumn(name = "sheet_music_id")
    private SheetMusic sheetMusic;

    public VoicePart() {

    }

    public VoicePart(String instrument, int partNumber, String instrumentGroup, int sheetCount, SheetMusic sheetMusic) {
        this.instrument = instrument;
        this.partNumber = partNumber;
        this.instrumentGroup = instrumentGroup;
        this.sheetCount = sheetCount;
        this.sheetMusic = sheetMusic;
    }
}
