package io.github.tainafernandes.mscreditappraiser.application.ex;

public class ErrorRequestCardException extends RuntimeException{
    public ErrorRequestCardException(String msg){
        super(msg);
    }
}
