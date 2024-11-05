package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressData(
        @NotBlank String street,
        @NotBlank String district,
        @NotBlank String city,
        @NotBlank String number,
        @NotBlank String modifier) {}
