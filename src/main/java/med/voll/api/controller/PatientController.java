package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.PatientData;
import med.voll.api.dto.ReadDoctorData;
import med.voll.api.dto.UpdatePatientData;
import med.voll.api.exceptions.NoEntityFoundException;
import med.voll.api.models.Patient;
import med.voll.api.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/patients")

public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public ResponseEntity<Page<PatientData>> readPatients(@PageableDefault(size = 2) Pageable pageable){
        return ResponseEntity.ok(patientRepository.findAll(pageable).map(PatientData::new));
    }
    @PostMapping
    public ResponseEntity<PatientData> createPatient(@RequestBody @Valid PatientData patientData,
                                                     UriComponentsBuilder uriComponentsBuilder){
        Patient patient=new Patient(patientData);
        patientRepository.save(patient);
        URI uri=uriComponentsBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientData(patient));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PatientData> readPatient(@PathVariable Long id) throws NoEntityFoundException {
        var patient=patientRepository.findById(id);
        if(patient.isPresent()){
            return ResponseEntity.ok(new PatientData(patient.get()));
        }
        throw new NoEntityFoundException("Patient");
    }
    @PutMapping()
    @Transactional
    public ResponseEntity<PatientData> updatePatient(@RequestBody @Valid UpdatePatientData patientData,
                                                     UriComponentsBuilder uriComponentsBuilder) throws NoEntityFoundException {
        var patient=patientRepository.findById(patientData.id());
        if(patient.isPresent()){
            patient.get().update(patientData);
            URI uri=uriComponentsBuilder.path("/patients/{id}").buildAndExpand(patient.get().getId()).toUri();
            return ResponseEntity.created(uri).body(new PatientData(patient.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PatientData(new Patient()));
    }
}
