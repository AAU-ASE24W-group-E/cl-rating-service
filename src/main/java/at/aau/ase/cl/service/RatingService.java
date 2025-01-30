package at.aau.ase.cl.service;

import at.aau.ase.cl.api.interceptors.exceptions.RatingAlreadyExistsException;
import at.aau.ase.cl.api.interceptors.exceptions.RatingNotFoundException;
import at.aau.ase.cl.api.model.Rating;
import at.aau.ase.cl.mapper.RatingMapper;
import at.aau.ase.cl.model.RatingEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class RatingService {

    @Transactional
    public Rating createRating(Rating rating) {
        boolean ratingWithSameId = RatingEntity.find("id = ?1", rating.getId()).firstResultOptional().isPresent();

        if (ratingWithSameId) {
            throw new RatingAlreadyExistsException("A rating with the same id already exists!");
        }
        RatingEntity ratingEntity = RatingMapper.INSTANCE.map(rating);
        ratingEntity.persistAndFlush();
        return RatingMapper.INSTANCE.map(ratingEntity);
    }

    public RatingEntity getRatingById(UUID id) {
        RatingEntity ratingEntity = RatingEntity.findById(id);
        if (ratingEntity != null) {
            return ratingEntity;
        }
        throw new RatingNotFoundException(String.format("The rating with id %s is not found!", id));
    }

    public List<RatingEntity> getRatingsForUsername(String username) {
        List<RatingEntity> ratings = RatingEntity.find("rated_username = ?1", username).list();
        return ratings;
    }

    public double getAverageRatingForUsername(String username) {
        List<RatingEntity> ratings = getRatingsForUsername(username);
        return getAverageRating(ratings);
    }

    public int getNumberOfRatingsForUsername(String username) {
        return getRatingsForUsername(username).size();
    }

    private double getAverageRating(List<RatingEntity> ratingEntityList) {
        if (ratingEntityList.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (RatingEntity ratingEntity : ratingEntityList) {
            if (ratingEntity != null) {
                sum += ratingEntity.rating;
            }
        }
        return sum / ratingEntityList.size();
    }
}
