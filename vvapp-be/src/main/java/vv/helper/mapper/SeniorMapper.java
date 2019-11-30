package vv.helper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import vv.dto.ParticipationDTO;
import vv.dto.SeniorDTO;
import vv.dto.SeniorDetailDTO;
import vv.model.AuthSchResponse;
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

    @Mappings({
            @Mapping(target = "name", source = "displayName"),
            @Mapping(target = "email", source = "mail"),
            @Mapping(target = "mobile", source = "mobile")
    })
    SeniorDTO autSchUserToSenior(AuthSchResponse authSchResponse);
}
