package at.aau.ase.cl.api.interceptors.exceptions;

public class RatingNotFoundException extends RuntimeException{
    public RatingNotFoundException(String message){
        super(message);
    }
}
