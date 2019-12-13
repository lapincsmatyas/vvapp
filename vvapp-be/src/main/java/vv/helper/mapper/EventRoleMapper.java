package vv.helper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vv.dto.EventRoleDTO;
import vv.model.EventRole;

@Mapper(componentModel = "spring")
public interface EventRoleMapper {
    EventRoleDTO eventRoleToEventRoleDto(EventRole eventRole);
    EventRole eventRoleDtoToEventType(EventRoleDTO eventRoleDTO);
}
