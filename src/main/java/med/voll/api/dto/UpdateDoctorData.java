package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateDoctorData(
        @NotNull Long id,
        String name,
        String email,
        AddressData address) {
}
