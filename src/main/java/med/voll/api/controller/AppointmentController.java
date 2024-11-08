package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.AppointmentData;
import med.voll.api.dto.AppointmentDetailsData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @PostMapping
    @Transactional
    public ResponseEntity setAppointment(@RequestBody @Valid AppointmentData data){
        System.out.println(data);
        return ResponseEntity.ok(new AppointmentDetailsData(null,null,null,null));
    }

}
