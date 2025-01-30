package at.aau.ase.cl.api;

import at.aau.ase.cl.api.interceptors.exceptions.InvalidRatingSameUserException;
import at.aau.ase.cl.api.model.Rating;
import at.aau.ase.cl.mapper.RatingMapper;
import at.aau.ase.cl.model.RatingEntity;
import at.aau.ase.cl.service.RatingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

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

    @GET
    @Path("/{id}")
    public Response getRating(@PathParam("id") UUID id){
        RatingEntity ratingEntity = ratingService.getRatingById(id);
        Rating rating = RatingMapper.INSTANCE.map(ratingEntity);
        return Response.ok(rating).build();
    }
}
