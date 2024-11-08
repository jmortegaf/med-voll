package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.Locked;
import med.voll.api.dto.ReadDoctorData;
import med.voll.api.dto.CreateDoctorData;
import med.voll.api.dto.UpdateDoctorData;
import med.voll.api.models.Doctor;
import med.voll.api.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.print.Doc;
import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/doctors")

public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    public ResponseEntity<ReadDoctorData> createDoctor(@RequestBody @Valid CreateDoctorData data,
                                       UriComponentsBuilder uriComponentsBuilder){
        Doctor doctor=doctorRepository.save(new Doctor(data));
        URI uri=uriComponentsBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new ReadDoctorData(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<ReadDoctorData>> readDoctors(@PageableDefault(size = 2) Pageable pageable){
        return ResponseEntity.ok(doctorRepository.findAll(pageable).map(ReadDoctorData::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ReadDoctorData> updateDoctor(@RequestBody @Valid UpdateDoctorData data){
        Doctor doctor=doctorRepository.getReferenceById(data.id());
        doctor.update(data);
        return ResponseEntity.ok(new ReadDoctorData(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id){
        Doctor doctor=doctorRepository.getReferenceById(id);
        System.out.println(doctor);
        doctor.setActive(false);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadDoctorData> readDoctor(@PathVariable Long id){
        Optional<Doctor> doctor=doctorRepository.findById(id);
        if(doctor.isPresent()){
            var _doctor=doctor.get();
            return ResponseEntity.ok(new ReadDoctorData(_doctor));
        }
        return ResponseEntity.ok(new ReadDoctorData(new Doctor()));
    }
}
