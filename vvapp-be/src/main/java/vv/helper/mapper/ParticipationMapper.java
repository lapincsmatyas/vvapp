package vv.helper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vv.dto.ParticipationDTO;
import vv.dto.ParticipationDetailDTO;
import vv.model.Participation;

@Mapper(componentModel = "spring")
public interface ParticipationMapper {
    ParticipationDTO participationToParticipationDto(Participation participation);
    Participation participationDtoToParticipation(ParticipationDTO participationDTO);

    ParticipationDetailDTO participationToParticipationDetailDto(Participation participation);
}
