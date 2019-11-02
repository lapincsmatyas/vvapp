package vv.helper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vv.dto.EventDTO;
import vv.dto.ParticipationDTO;
import vv.model.Event;
import vv.model.Participation;

@Mapper(componentModel = "spring")
public interface ParticipationMapper {
    ParticipationMapper INSTANCE = Mappers.getMapper(ParticipationMapper.class);
    ParticipationDTO participationToParticipationDto(Participation participation);
    Participation participationDtoToParticipation(ParticipationDTO participationDTO);
}
