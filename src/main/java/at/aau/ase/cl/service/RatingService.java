package at.aau.ase.cl.service;

import at.aau.ase.cl.api.model.Rating;
import at.aau.ase.cl.mapper.RatingMapper;
import at.aau.ase.cl.model.RatingEntity;
import jakarta.transaction.Transactional;
import io.quarkus.logging.Log;

public class RatingService {

    @Transactional
    public Rating createRating(Rating rating){
        boolean ratingWithSameId = RatingEntity.find("id = ?1", rating.getId()).firstResultOptional().isPresent();

        if(ratingWithSameId){
            Log.error("A rating with the same id already exists!");
        }else{
            RatingEntity ratingEntity = RatingMapper.INSTANCE.map(rating);
            ratingEntity.persistAndFlush();
            return RatingMapper.INSTANCE.map(ratingEntity);
        }
        return null;
    }
}
