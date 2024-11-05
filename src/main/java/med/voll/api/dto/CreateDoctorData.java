package med.voll.api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateDoctorData(
        @NotBlank String name,
        @NotBlank String email,
        @JsonAlias("document_id") @NotBlank @Pattern(regexp = "\\d{4,6}") String documentId,
        @NotNull String specialty,
        @NotNull @Valid AddressData address) {}
