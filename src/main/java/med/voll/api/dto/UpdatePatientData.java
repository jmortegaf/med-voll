package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record UpdatePatientData(
        @NotNull Long id,
        String patientName,
        String email){}
