package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.ReadDoctorData;
import med.voll.api.dto.CreateDoctorData;
import med.voll.api.dto.UpdateDoctorData;
import med.voll.api.models.Doctor;
import med.voll.api.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/doctors")

public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    public void createDoctor(@RequestBody @Valid CreateDoctorData data){
        doctorRepository.save(new Doctor(data));
    }

    @GetMapping
    public Page<ReadDoctorData> readDoctors(@PageableDefault(size = 2) Pageable pageable){
        return doctorRepository.findAll(pageable).map(ReadDoctorData::new);
    }

    @PutMapping
    @Transactional
    void updateDoctor(@RequestBody @Valid UpdateDoctorData data){
        Doctor doctor=doctorRepository.getReferenceById(data.id());
        doctor.update(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    void deleteDoctor(@PathVariable Long id){
        Doctor doctor=doctorRepository.getReferenceById(id);
        System.out.println(doctor);
        doctor.setActive(false);
    }
}
