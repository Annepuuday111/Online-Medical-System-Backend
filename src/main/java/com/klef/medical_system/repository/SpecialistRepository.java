package com.klef.medical_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.klef.medical_system.model.Specialist;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, Integer> {
}
