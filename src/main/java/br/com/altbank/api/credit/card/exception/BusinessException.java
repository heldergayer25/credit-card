package br.com.altbank.api.credit.card.exception;

/**
 * Classe para as exceções de regras de negócio.
 */
public class BusinessException extends Exception {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}