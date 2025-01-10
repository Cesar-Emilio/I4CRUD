package com.example.demo.supply.control;

import com.example.demo.supply.model.Supply;
import com.example.demo.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supply")
public class SupplyController {

    private final SupplyService supplyService;

    @Autowired
    public SupplyController(SupplyService supplyService) {
        this.supplyService = supplyService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllSupplies() {
        return supplyService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Message> addSupply(@RequestBody Supply supply) {
        return supplyService.saveSupply(supply);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> updateSupply(@PathVariable Long id, @RequestBody Supply supply) {
        return supplyService.updateSupply(id, supply);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> deleteSupply(@PathVariable Long id) {
        return supplyService.deleteSupply(id);
    }
}