package at.aau.ase.cl.api.interceptors.exceptions;

public class InvalidRatingSameUserException extends RuntimeException{
    public InvalidRatingSameUserException(String message){
        super(message);
    }
}
