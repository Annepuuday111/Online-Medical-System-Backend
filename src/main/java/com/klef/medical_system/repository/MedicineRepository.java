package com.klef.medical_system.repository;

import com.klef.medical_system.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findByAppointmentId(Long appointmentId);
}
