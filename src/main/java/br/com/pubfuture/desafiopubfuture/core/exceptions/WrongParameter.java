package br.com.pubfuture.desafiopubfuture.core.exceptions;

public class WrongParameter extends RuntimeException{

    public WrongParameter(String message) {
        super(message);
    }

    public WrongParameter(String message, Throwable cause) {
        super(message, cause);
    }

}
