package com.example.demo.office.control;

import com.example.demo.office.model.Office;
import com.example.demo.office.model.OfficeRepository;
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
public class OfficeService {

    private static final Logger logger = LoggerFactory.getLogger(OfficeService.class);

    private final OfficeRepository officeRepository;

    @Autowired
    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Office> offices = officeRepository.findAll();
        logger.info("Lista de consultas obtenida correctamente");
        return new ResponseEntity<>(new Message(offices, "Lista de consultas", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Message> saveOffice(Office office) {
        officeRepository.save(office);
        logger.info("Consulta añadida correctamente");
        return new ResponseEntity<>(new Message(null, "Consulta añadida", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<Message> updateOffice(Long id, Office office) {
        Optional<Office> optionalOffice = officeRepository.findById(id);
        if (optionalOffice.isPresent()) {
            Office existingOffice = optionalOffice.get();
            existingOffice.setDateOfCreation(office.getDateOfCreation());
            existingOffice.setDiagnosis(office.getDiagnosis());
            existingOffice.setTreatment(office.getTreatment());
            existingOffice.setDoctor(office.getDoctor());
            existingOffice.setPatient(office.getPatient());
            existingOffice.setStatus(office.isStatus());
            officeRepository.save(existingOffice);
            logger.info("Consulta actualizada correctamente");
            return new ResponseEntity<>(new Message(null, "Consulta actualizada", TypesResponse.SUCCESS), HttpStatus.OK);
        } else {
            logger.warn("Consulta no encontrada");
            return new ResponseEntity<>(new Message(null, "Consulta no encontrada", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<Message> deleteOffice(Long id) {
        if (officeRepository.existsById(id)) {
            officeRepository.deleteById(id);
            logger.info("Consulta eliminada correctamente");
            return new ResponseEntity<>(new Message(null, "Consulta eliminada", TypesResponse.SUCCESS), HttpStatus.OK);
        } else {
            logger.warn("Consulta no encontrada");
            return new ResponseEntity<>(new Message(null, "Consulta no encontrada", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
    }
}