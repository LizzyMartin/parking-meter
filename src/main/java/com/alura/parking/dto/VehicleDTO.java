package com.alura.parking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {

    @NotBlank(message = "Complete the field conductorId")
    private String conductorId;

    @NotBlank(message = "Complete the field brand")
    private String brand;

    @NotBlank(message = "Complete the field plate")
    private String plate;


}
