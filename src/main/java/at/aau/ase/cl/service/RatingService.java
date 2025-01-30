package at.aau.ase.cl.service;

import at.aau.ase.cl.api.model.Rating;
import at.aau.ase.cl.mapper.RatingMapper;
import at.aau.ase.cl.model.RatingEntity;
import jakarta.transaction.Transactional;
import io.quarkus.logging.Log;

import java.util.List;
import java.util.OptionalDouble;
import java.util.UUID;

public class RatingService {

    @Transactional
    public Rating createRating(Rating rating) {
        boolean ratingWithSameId = RatingEntity.find("id = ?1", rating.getId()).firstResultOptional().isPresent();

        if (ratingWithSameId) {
            Log.error("A rating with the same id already exists!");
        } else {
            RatingEntity ratingEntity = RatingMapper.INSTANCE.map(rating);
            ratingEntity.persistAndFlush();
            return RatingMapper.INSTANCE.map(ratingEntity);
        }
        return null;
    }

    public RatingEntity getRatingById(UUID id) {
        RatingEntity ratingEntity = RatingEntity.findById(id);
        if (ratingEntity != null) {
            return ratingEntity;
        }
        Log.debugf("The rating with id %s is not found!", id);
        return null;
    }

    public List<RatingEntity> getRatingsForUsername(String username) {
        List<RatingEntity> ratings = RatingEntity.find("rated_username = ?1", username).list();
        return ratings;
    }

    public double getAverageRatingForUsername(String username) {
        List<RatingEntity> ratings = getRatingsForUsername(username);
        return getAverageRating(ratings);
    }

    public int getNumberOfRatingsForUsername(String username){
        return getRatingsForUsername(username).size();
    }

    private double getAverageRating(List<RatingEntity> ratingEntityList){
        if(ratingEntityList.isEmpty()){
            return 0;
        }
        double sum = 0;
        for(RatingEntity ratingEntity : ratingEntityList){
            if(ratingEntity != null){
                sum += ratingEntity.rating;
            }
        }
        return sum / ratingEntityList.size();
    }
}
