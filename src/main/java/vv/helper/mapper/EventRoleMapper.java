package vv.helper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vv.dto.EventRoleDTO;
import vv.dto.EventTypeDTO;
import vv.model.EventRole;
import vv.model.EventType;

@Mapper(componentModel = "spring")
public interface EventRoleMapper {
    EventRoleMapper INSTANCE = Mappers.getMapper(EventRoleMapper.class);
    EventRoleDTO eventRoleToEventRoleDto(EventRole eventRole);
    EventRole eventRoleDtoToEventType(EventRoleDTO eventRoleDTO);
}
