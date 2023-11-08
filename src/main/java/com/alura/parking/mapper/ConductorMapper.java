package com.alura.parking.mapper;

import com.alura.parking.dto.ConductorDTO;
import com.alura.parking.entity.Conductor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConductorMapper {

    ConductorMapper INSTANCE = Mappers.getMapper(ConductorMapper.class);

    ConductorDTO conductorToConductorDTO(Conductor conductor);

    Conductor conductorDTOToConductor(ConductorDTO conductorDTO);

}
