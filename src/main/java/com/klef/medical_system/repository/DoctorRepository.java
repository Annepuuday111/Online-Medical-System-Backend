package com.klef.medical_system.repository;

import com.klef.medical_system.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Optional<Doctor> findByEmail(String email);
    
    List<Doctor> findByGender(String gender);
    
    Doctor findByDoctorId(String doctorId);
}
