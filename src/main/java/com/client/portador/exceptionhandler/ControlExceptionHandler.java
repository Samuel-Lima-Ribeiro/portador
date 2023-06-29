package com.client.portador.exceptionhandler;

import com.client.portador.exception.CardHolderAlreadyExistException;
import com.client.portador.exception.CardHolderNotFoundException;
import com.client.portador.exception.CardNotFoundException;
import com.client.portador.exception.ClientInvalidException;
import com.client.portador.exception.CreditAnalysisDeniedException;
import com.client.portador.exception.CreditAnalysisNotFoundException;
import com.client.portador.exception.LimitInvalidException;
import jakarta.validation.ConstraintViolationException;
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
    public static final String UNPROCESSABLE_ENTITY = "https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status/422";

    @ExceptionHandler(CreditAnalysisNotFoundException.class)
    public ProblemDetail creditAnalysisNotFoundHandleException(CreditAnalysisNotFoundException exception) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Análise de Crédito não encontrada");
        problemDetail.setType(URI.create(NOT_FOUND));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        problemDetail.setDetail(exception.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(ClientInvalidException.class)
    public ProblemDetail clientInvalidHandleException(ClientInvalidException exception) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Cliente inválido");
        problemDetail.setType(URI.create(UNPROCESSABLE_ENTITY));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        problemDetail.setDetail(exception.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(CreditAnalysisDeniedException.class)
    public ProblemDetail creditAnalysisDeniedHandleException(CreditAnalysisDeniedException exception) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Análise de Crédito negada");
        problemDetail.setType(URI.create(UNPROCESSABLE_ENTITY));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        problemDetail.setDetail(exception.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(CardHolderAlreadyExistException.class)
    public ProblemDetail cardHolderAlreadyExistHandleException(CardHolderAlreadyExistException exception) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Portador já existe");
        problemDetail.setType(URI.create(UNPROCESSABLE_ENTITY));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        problemDetail.setDetail(exception.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail constraintViolationExceptionHandle(ConstraintViolationException exception) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status/400"));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        problemDetail.setDetail(exception.getConstraintViolations().toString());
        return problemDetail;
    }

    @ExceptionHandler(CardHolderNotFoundException.class)
    public ProblemDetail cardHolderNotFoundHandleException(CardHolderNotFoundException exception) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Portador não encontrado");
        problemDetail.setType(URI.create(NOT_FOUND));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        problemDetail.setDetail(exception.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(LimitInvalidException.class)
    public ProblemDetail limitInvalidExceptionHandleException(LimitInvalidException exception) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Limite inválido para o cartão");
        problemDetail.setType(URI.create(UNPROCESSABLE_ENTITY));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        problemDetail.setDetail(exception.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ProblemDetail cardNotFoundHandleException(CardNotFoundException exception) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Cartão não encontrado");
        problemDetail.setType(URI.create(NOT_FOUND));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        problemDetail.setDetail(exception.getMessage());
        return problemDetail;
    }
}
