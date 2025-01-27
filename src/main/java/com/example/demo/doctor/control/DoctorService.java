    package com.example.demo.Doctor.control;

    import com.example.demo.Doctor.model.Doctor;
    import com.example.demo.Doctor.model.DoctorRepository;
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
    public class DoctorService {

        private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);

        private final DoctorRepository doctorRepository;

        @Autowired
        public DoctorService(DoctorRepository doctorRepository) {
            this.doctorRepository = doctorRepository;
        }

        @Transactional(readOnly = true)
        public ResponseEntity<Message> findAll() {
            List<Doctor> doctors = doctorRepository.findAll();
            logger.info("Lista de doctores obtenida correctamente");
            return new ResponseEntity<>(new Message(doctors, "Lista de doctores", TypesResponse.SUCCESS), HttpStatus.OK);
        }

        @Transactional
        public ResponseEntity<Message> saveDoctor(Doctor doctor) {
            doctorRepository.save(doctor);
            logger.info("Doctor saved successfully");
            return new ResponseEntity<>(new Message("Doctor añadido", TypesResponse.SUCCESS), HttpStatus.CREATED);
        }

        @Transactional
        public ResponseEntity<Message> updateDoctor(Long id, Doctor doctor) {
            Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
            if (optionalDoctor.isPresent()) {
                Doctor existingDoctor = optionalDoctor.get();
                existingDoctor.setName(doctor.getName());
                existingDoctor.setLastName(doctor.getLastName());
                existingDoctor.setCurp(doctor.getCurp());
                existingDoctor.setPhone(doctor.getPhone());
                existingDoctor.setAge(doctor.getAge());
                existingDoctor.setSpeciality(doctor.getSpeciality());
                existingDoctor.setStatus(doctor.isStatus());
                doctorRepository.save(existingDoctor);
                logger.info("Doctor actualizado correctamente");
                return new ResponseEntity<>(new Message("Doctor actualiado", TypesResponse.SUCCESS), HttpStatus.OK);
            } else {
                logger.warn("Doctor no encontrado");
                return new ResponseEntity<>(new Message("Doctor no encontrado", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
            }
        }

        @Transactional
        public ResponseEntity<Message> deleteDoctor(Long id) {
            if (doctorRepository.existsById(id)) {
                doctorRepository.deleteById(id);
                logger.info("Doctor eliminado correctamente");
                return new ResponseEntity<>(new Message(null, "Doctor eliminado", TypesResponse.SUCCESS), HttpStatus.OK);
            } else {
                logger.warn("Doctor no encontrado");
                return new ResponseEntity<>(new Message(null, "Doctor no encontrado", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
            }
        }
    }
