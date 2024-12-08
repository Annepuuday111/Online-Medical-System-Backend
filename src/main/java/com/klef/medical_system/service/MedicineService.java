package com.klef.medical_system.service;

import com.klef.medical_system.model.Medicine;
import com.klef.medical_system.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    public boolean isMedicineAlreadySuggested(Long appointmentId) {
        return !medicineRepository.findByAppointmentId(appointmentId).isEmpty();
    }

    public Medicine suggestMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    public List<Medicine> getSuggestedMedicines(Long appointmentId) {
        return medicineRepository.findByAppointmentId(appointmentId);
    }
}
