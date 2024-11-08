package med.voll.api.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import med.voll.api.dto.PatientData;
import med.voll.api.dto.UpdatePatientData;

@Entity
@Table(name = "patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patientName;
    private String documentId;
    private String email;
    private Address address;

    public Patient(PatientData patientData){
        this.patientName=patientData.patientName();
        this.documentId=patientData.documentId();
        this.email=patientData.email();
        this.address=patientData.address();
    }

    public void update(@Valid UpdatePatientData patientData) {
        patientName=patientData.patientName()!=null?patientData.patientName():patientName;
        email=patientData.email()!=null?patientData.email():email;
    }
}
