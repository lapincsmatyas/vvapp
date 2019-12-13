package vv.helper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vv.dto.SeniorGroupDTO;
import vv.model.SeniorGroup;

@Mapper(componentModel = "spring")
public interface SeniorGroupMapper {
    SeniorGroupDTO groupToGroupDto(SeniorGroup group);
    SeniorGroup groupDtoToGroup(SeniorGroupDTO seniorGroupDto);
}
