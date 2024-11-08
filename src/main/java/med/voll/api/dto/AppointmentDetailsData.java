package med.voll.api.dto;

import java.time.LocalDateTime;

public record AppointmentDetailsData(
        Long id,
        Long doctorId,
        Long patientId,
        LocalDateTime date) {}
