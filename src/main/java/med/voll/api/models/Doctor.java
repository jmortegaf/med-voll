package med.voll.api.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import med.voll.api.dto.CreateDoctorData;
import med.voll.api.dto.UpdateDoctorData;

@Entity
@Table(name = "doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@ToString
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String documentId;
    @Enumerated(EnumType.STRING)
    private DoctorSpecialty specialty;
    @Embedded
    private Address address;
    private Boolean active=false;

    public Doctor(CreateDoctorData data) {
        this.name= data.name();;
        this.email= data.email();
        this.documentId= data.documentId();
        this.specialty=DoctorSpecialty.fromString(data.specialty());
        this.address=new Address(data.address());
    }

    public void update(@Valid UpdateDoctorData data) {
        name=data.name()!=null?data.name():name;
        email=data.email()!=null? data.email():email;
        address.update(data.address());
    }

}
