package med.voll.api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentData(
        Long doctorId,
        @NotNull Long patientId,
        @NotNull @Future LocalDateTime date) {}
