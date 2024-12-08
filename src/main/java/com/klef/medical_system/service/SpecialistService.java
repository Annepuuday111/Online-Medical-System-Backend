package com.klef.medical_system.service;

import com.klef.medical_system.exception.ResourceNotFoundException;
import com.klef.medical_system.model.Specialist;
import com.klef.medical_system.repository.SpecialistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialistService {

    @Autowired
    private SpecialistRepository specialistRepository;

    public List<Specialist> getAllSpecialists() {
        return specialistRepository.findAll();
    }

    public Specialist createSpecialist(Specialist specialist) {
        return specialistRepository.save(specialist);
    }

    public Specialist getSpecialistById(int id) {
        return specialistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialist not found with id: " + id));
    }

    public Specialist updateSpecialist(int id, Specialist specialistDetails) {
        Specialist specialist = getSpecialistById(id);
        specialist.setSpecialistName(specialistDetails.getSpecialistName());
        return specialistRepository.save(specialist);
    }

    public void deleteSpecialist(int id) {
        Specialist specialist = getSpecialistById(id);
        specialistRepository.delete(specialist);
    }
}
