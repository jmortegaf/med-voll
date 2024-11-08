package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import med.voll.api.models.Doctor;
import med.voll.api.models.DoctorSpecialty;

public record ReadDoctorData(
        Long id,
        @NotBlank String name,
        @NotBlank String email,
        @NotNull DoctorSpecialty specialty,
        @NotNull Boolean active) {

    public ReadDoctorData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(),doctor.getEmail(),doctor.getSpecialty(),doctor.getActive());
    }
}
