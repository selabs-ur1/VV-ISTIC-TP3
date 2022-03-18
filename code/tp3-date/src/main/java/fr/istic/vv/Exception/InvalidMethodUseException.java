package fr.istic.vv.Exception;

import java.util.concurrent.ExecutionException;

public class InvalidMethodUseException extends ExecutionException {

    public InvalidMethodUseException(String methodName, String message){
        super("Invalid call for " + methodName + message);
    }
}
