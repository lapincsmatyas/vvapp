package vv.helper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vv.dto.EventDTO;
import vv.dto.EventDetailDTO;
import vv.model.Event;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDTO eventToEventDto(Event event);
    Event eventDtoToEvent(EventDTO eventDTO);

    EventDetailDTO eventToEventDetailDto(Event event);
}
