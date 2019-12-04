package vv.helper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vv.dto.EventRoleDTO;
import vv.dto.UserRoleDTO;
import vv.model.EventRole;
import vv.model.UserRole;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    UserRoleMapper INSTANCE = Mappers.getMapper(UserRoleMapper.class);
    UserRole userRoleDtoToUserRole(UserRoleDTO userRole);
    UserRoleDTO userRoleToUserRoleDTO(UserRole userRole);
}
