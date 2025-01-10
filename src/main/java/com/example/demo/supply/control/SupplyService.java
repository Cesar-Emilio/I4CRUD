package com.example.demo.supply.control;

import com.example.demo.supply.model.Supply;
import com.example.demo.supply.model.SupplyRepository;
import com.example.demo.utils.Message;
import com.example.demo.utils.TypesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class SupplyService {

    private static final Logger logger = LoggerFactory.getLogger(SupplyService.class);

    private final SupplyRepository supplyRepository;

    @Autowired
    public SupplyService(SupplyRepository supplyRepository) {
        this.supplyRepository = supplyRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Supply> supplies = supplyRepository.findAll();
        logger.info("Supply list fetched successfully");
        return new ResponseEntity<>(new Message(supplies, "Supply list", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Message> saveSupply(Supply supply) {
        supplyRepository.save(supply);
        logger.info("Supply saved successfully");
        return new ResponseEntity<>(new Message(null, "Supply added", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<Message> updateSupply(Long id, Supply supply) {
        Optional<Supply> optionalSupply = supplyRepository.findById(id);
        if (optionalSupply.isPresent()) {
            Supply existingSupply = optionalSupply.get();
            existingSupply.setName(supply.getName());
            existingSupply.setDescription(supply.getDescription());
            existingSupply.setQuantity(supply.getQuantity());
            existingSupply.setStatus(supply.isStatus());
            supplyRepository.save(existingSupply);
            logger.info("Supply updated successfully");
            return new ResponseEntity<>(new Message(null, "Supply updated", TypesResponse.SUCCESS), HttpStatus.OK);
        } else {
            logger.warn("Supply not found");
            return new ResponseEntity<>(new Message(null, "Supply not found", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<Message> deleteSupply(Long id) {
        if (supplyRepository.existsById(id)) {
            supplyRepository.deleteById(id);
            logger.info("Supply deleted successfully");
            return new ResponseEntity<>(new Message(null, "Supply deleted", TypesResponse.SUCCESS), HttpStatus.OK);
        } else {
            logger.warn("Supply not found");
            return new ResponseEntity<>(new Message(null, "Supply not found", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
    }
}