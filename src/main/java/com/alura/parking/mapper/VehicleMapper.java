package com.alura.parking.mapper;

import com.alura.parking.dto.VehicleDTO;
import com.alura.parking.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    VehicleDTO vehicleToVehicleDTO(Vehicle vehicle);

    Vehicle vehicleDTOToVehicle(VehicleDTO vehicleDTO);
}
