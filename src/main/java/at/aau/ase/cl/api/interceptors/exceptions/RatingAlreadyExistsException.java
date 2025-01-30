package at.aau.ase.cl.api.interceptors.exceptions;

public class RatingAlreadyExistsException extends RuntimeException{
    public RatingAlreadyExistsException(String message){
        super(message);
    }
}
