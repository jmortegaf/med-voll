package med.voll.api.dto;

import med.voll.api.models.Address;
import med.voll.api.models.Doctor;
import med.voll.api.models.Patient;

public record PatientData(
                String patientName,
                String documentId,
                String email,
                Address address) {

    public PatientData(Patient patient) {
        this(patient.getPatientName(), patient.getDocumentId(), patient.getEmail(),patient.getAddress());
    }
}
