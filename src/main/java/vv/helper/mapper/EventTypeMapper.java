package vv.helper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vv.dto.EventTypeDTO;
import vv.dto.EventTypeDetailDTO;
import vv.model.EventType;

@Mapper(componentModel = "spring")
public interface EventTypeMapper {
    EventTypeMapper INSTANCE = Mappers.getMapper(EventTypeMapper.class);
    EventTypeDTO eventTypeToEventTypeDto(EventType event);
    EventTypeDetailDTO eventTypeToEventTypeDetailDto(EventType event);
    EventType eventTypeDtoToEventType(EventTypeDTO eventTypeDTO);
}
