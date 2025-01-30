package at.aau.ase.cl.api.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

import java.util.UUID;

public class Rating {

    public UUID id;

    /* Username of the user that gives a rating */
    @Size(min = 1, max = 255)
    @NotNull
    public String rating_username;

    /* Username of the user that receives a rating */
    @Size(min = 1, max = 255)
    @NotNull
    public String rated_username;

    /* If users rate each other, it has to be at least
    1 or at most 5 stars - if the rating is not done it can be null for now */
    @Min(1)
    @Max(5)
    public Integer rating; 

    public Rating() {
    }

    public Rating(String rating_username, String rated_username, Integer rating ) {
        this.rating_username = rating_username;
        this.rated_username = rated_username;
        this.rating = rating;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRating_username() {
        return rating_username;
    }

    public void setRating_username(String rating_username) {
        this.rating_username = rating_username;
    }

    public String getRated_username() {
        return rated_username;
    }

    public void setRated_username(String rated_username) {
        this.rated_username = rated_username;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}