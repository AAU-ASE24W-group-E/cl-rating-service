package at.aau.ase.cl.mapper;

import at.aau.ase.cl.api.model.Rating;
import at.aau.ase.cl.model.RatingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RatingMapper {
    RatingMapper INSTANCE = Mappers.getMapper(RatingMapper.class);

    RatingEntity map(Rating rating);

    Rating map(RatingEntity ratingEntity);
}