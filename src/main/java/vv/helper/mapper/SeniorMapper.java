package vv.helper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vv.dto.SeniorDTO;
import vv.model.Senior;

@Mapper(componentModel = "spring")
public interface SeniorMapper {
    SeniorMapper INSTANCE = Mappers.getMapper(SeniorMapper.class);
    SeniorDTO seniorToSeniorDto(Senior senior);
    Senior seniorDtoToSenior(SeniorDTO seniorDTO);
}
