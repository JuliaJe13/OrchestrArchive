package de.dhbw.orchestrachive.orchestrarchive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SheetMusicNotFoundException.class)
    public ProblemDetail handleSheetMusicNotFound(SheetMusicNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Resource not found");
        return problem;
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ProblemDetail handleCategoryNotFound(CategoryNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Resource not found");
        return problem;
    }

    @ExceptionHandler(VoicePartNotFoundException.class)
    public ProblemDetail handleVoicePartNotFound(VoicePartNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Resource not found");
        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage()));

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, "Request validation failed");
        problem.setTitle("Validation error");
        problem.setProperty("fieldErrors", fieldErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleAnyOther(Exception ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected server error");
        problem.setTitle("Internal server error");
        problem.setProperty("exception", ex.getClass().getSimpleName());
        return problem;
    }

}
