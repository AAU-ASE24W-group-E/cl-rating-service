package at.aau.ase.cl.api.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

import java.util.UUID;

public class Rating {
    public UUID id;

    @Size(min = 1, max = 255)
    @NotNull
    public String owner_username;

    @Size(min = 1, max = 255)
    @NotNull
    public String reader_username;

    /* If users rate each other, it has to be at least
    1 or at most 5 stars - if the rating is not done it can be null for now */
    @Min(1)
    @Max(5)
    public Integer rating; 

    public Rating() {
    }

    public Rating(String owner_username, String reader_username, Integer rating ) {
        this.owner_username = owner_username;
        this.reader_username = reader_username;
        this.rating = rating;
    }
}