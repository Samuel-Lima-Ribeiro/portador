package com.client.portador.exceptionhandler;

import com.client.portador.exception.ClientInvalidException;
import com.client.portador.exception.CreditAnalysisDeniedException;
import com.client.portador.exception.CreditAnalysisNotFoundException;
import java.net.URI;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControlExceptionHandler {

    public static final String TIMESTAMP = "timestamp";
    public static final String NOT_FOUND = "https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status/404";

    @ExceptionHandler(CreditAnalysisNotFoundException.class)
    public ProblemDetail creditAnalysisNotFoundHandleException(CreditAnalysisNotFoundException exception) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Credit Analysis not found");
        problemDetail.setType(URI.create(NOT_FOUND));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        problemDetail.setDetail(exception.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(ClientInvalidException.class)
    public ProblemDetail clientInvalidHandleException(ClientInvalidException exception) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        // melhorar o titulo
        problemDetail.setTitle("Client Invalid");
        problemDetail.setType(URI.create(NOT_FOUND));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        problemDetail.setDetail(exception.getMessage());
        return problemDetail;
    }

    // talvez invalida no lugar de negada?
    @ExceptionHandler(CreditAnalysisDeniedException.class)
    public ProblemDetail creditAnalysisDeniedHandleException(CreditAnalysisDeniedException exception) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        // melhorar o titulo
        problemDetail.setTitle("Credit Analysis Invalid");
        problemDetail.setType(URI.create(NOT_FOUND));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        problemDetail.setDetail(exception.getMessage());
        return problemDetail;
    }
}
