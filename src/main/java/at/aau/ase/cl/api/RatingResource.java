package at.aau.ase.cl.api;

import at.aau.ase.cl.api.interceptors.exceptions.InvalidRatingSameUserException;
import at.aau.ase.cl.api.model.Rating;
import at.aau.ase.cl.service.RatingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/ratings")
@Produces(MediaType.APPLICATION_JSON)
public class RatingResource {

    @Inject
    RatingService ratingService;

    @POST
    @Path("/")
    public Response createRating(Rating rating) {
        if (rating.getRating_username().equals(rating.getRated_username())) {
            throw new InvalidRatingSameUserException("Users cannot rate themselves!");
        }
        Rating createdRating = ratingService.createRating(rating);
        return Response.ok(createdRating).build();
    }

}
