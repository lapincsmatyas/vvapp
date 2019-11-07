package vv.helper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vv.dto.ParticipationDTO;
import vv.dto.ReviewDTO;
import vv.dto.SeniorDTO;
import vv.dto.SeniorDetailDTO;
import vv.model.Participation;
import vv.model.Review;
import vv.model.Senior;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);
    ReviewDTO reviewToReviewDto(Review review);
    Review reviewDtoToReview(ReviewDTO reviewDTO);
}
