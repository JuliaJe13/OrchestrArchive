package de.dhbw.orchestrachive.orchestrarchive.exception;

public class SheetMusicNotFoundException extends RuntimeException {
    public SheetMusicNotFoundException(Long id) {
        super("Sheet Music with id " + id + " not found");
    }
}
