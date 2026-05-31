package de.dhbw.orchestrachive.orchestrarchive.exception;

public class VoicePartNotFoundException extends RuntimeException {
    public VoicePartNotFoundException(Long id) {
        super("Voice Part with id " + id + " not found");
    }
}
