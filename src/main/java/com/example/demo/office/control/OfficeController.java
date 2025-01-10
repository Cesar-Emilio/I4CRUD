package com.example.demo.office.control;

import com.example.demo.office.model.Office;
import com.example.demo.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/office")
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllOffices() {
        return officeService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Message> addOffice(@RequestBody Office office) {
        return officeService.saveOffice(office);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> updateOffice(@PathVariable Long id, @RequestBody Office office) {
        return officeService.updateOffice(id, office);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> deleteOffice(@PathVariable Long id) {
        return officeService.deleteOffice(id);
    }
}
