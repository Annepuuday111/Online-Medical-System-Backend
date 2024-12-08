package com.klef.medical_system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.medical_system.exception.ResourceNotFoundException;
import com.klef.medical_system.model.Specialist;
import com.klef.medical_system.repository.SpecialistRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/specialists")
public class SpecialistController {

    @Autowired
    private SpecialistRepository specialistRepository;

    @GetMapping
    public List<Specialist> getAllSpecialists() {
        return specialistRepository.findAll();
    }

    @PostMapping
    public Specialist createSpecialist(@RequestBody Specialist specialist) {
        return specialistRepository.save(specialist);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specialist> getSpecialistById(@PathVariable int id) {
        Specialist specialist = specialistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialist not found with id: " + id));
        return ResponseEntity.ok(specialist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Specialist> updateSpecialist(@PathVariable int id, @RequestBody Specialist specialistDetails) {
        Specialist specialist = specialistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialist not found with id: " + id));

        specialist.setSpecialistName(specialistDetails.getSpecialistName());

        Specialist updatedSpecialist = specialistRepository.save(specialist);
        return ResponseEntity.ok(updatedSpecialist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteSpecialist(@PathVariable int id) {
        Specialist specialist = specialistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialist not found with id: " + id));

        specialistRepository.delete(specialist);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/count")
    public ResponseEntity<Long> getSpecialistCount() {
        long count = specialistRepository.count();
        return ResponseEntity.ok(count);
    }

}
