package vv.helper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vv.dto.ParticipationDTO;
import vv.dto.SeniorDTO;
import vv.dto.SeniorDetailDTO;
import vv.model.Participation;
import vv.model.Senior;

@Mapper(componentModel = "spring")
public interface SeniorMapper {
    SeniorMapper INSTANCE = Mappers.getMapper(SeniorMapper.class);
    SeniorDTO seniorToSeniorDto(Senior senior);
    SeniorDetailDTO seniorToSeniorDetailDto(Senior senior);
    Senior seniorDtoToSenior(SeniorDTO seniorDTO);


    ParticipationDTO participationToParticipationDto(Participation participation);
    Participation participationDtoToParticipation(ParticipationDTO participationDTO);
}
