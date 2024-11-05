package med.voll.api.controller;

import med.voll.api.dto.DoctorRegisterData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")

public class DoctorController {

    @PostMapping
    public void createDoctor(@RequestBody DoctorRegisterData data){
        System.out.println("request arrived");
        System.out.println(data);
    }
}
