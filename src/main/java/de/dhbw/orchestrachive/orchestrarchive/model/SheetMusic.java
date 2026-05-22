package de.dhbw.orchestrachive.orchestrarchive.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class SheetMusic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;
    private String composer;
    private String arranger;
    private int year;
    private String publisher;

    @ManyToMany
    @JoinTable(
            name = "sheet_music_categories",
            joinColumns = @JoinColumn(name = "sheet_music_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    public SheetMusic() {

    }
}
