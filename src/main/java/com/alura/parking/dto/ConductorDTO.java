package com.alura.parking.dto;

import com.alura.parking.enums.PaymentType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConductorDTO {

    @NotBlank(message = "Complete the field name")
    private String name;

    @NotBlank(message = "Complete the field address")
    private String address;

    @CPF(message = "Please write a valid CPF")
    @NotBlank(message = "Complete the field cpf")
    private String cpf;

    @Email(message = "Please write a valid email")
    @NotBlank(message = "Complete the field email")
    private String email;

    @NotBlank(message = "Complete the field favoritePaymentType")
    private PaymentType favoritePaymentType;

}
