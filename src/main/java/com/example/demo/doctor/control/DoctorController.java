package com.example.demo.Doctor.control;

import com.example.demo.Doctor.model.Doctor;
import com.example.demo.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllDoctors() {
        return doctorService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Message> addDoctor(@RequestBody Doctor doctor) {
        return doctorService.saveDoctor(doctor);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        return doctorService.updateDoctor(id, doctor);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> deleteDoctor(@PathVariable Long id) {
        return doctorService.deleteDoctor(id);
    }
}