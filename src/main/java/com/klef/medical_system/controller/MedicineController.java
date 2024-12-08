package com.klef.medical_system.controller;

import com.klef.medical_system.model.Medicine;
import com.klef.medical_system.service.MedicineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @PostMapping("/medicine/{appointmentId}")
    public ResponseEntity<?> suggestMedicine(@PathVariable Long appointmentId, @Valid @RequestBody Medicine medicine) {
        boolean isMedicineAlreadySuggested = medicineService.isMedicineAlreadySuggested(appointmentId);
        if (isMedicineAlreadySuggested) {
            return ResponseEntity.badRequest().body("Medicine has already been suggested for this appointment.");
        }
        medicine.setAppointmentId(appointmentId);
        Medicine suggestedMedicine = medicineService.suggestMedicine(medicine);
        return ResponseEntity.ok(suggestedMedicine);
    }

    @GetMapping("/medicine/{appointmentId}")
    public ResponseEntity<List<Medicine>> getSuggestedMedicines(@PathVariable Long appointmentId) {
        List<Medicine> medicines = medicineService.getSuggestedMedicines(appointmentId);
        return ResponseEntity.ok(medicines);
    }
}
