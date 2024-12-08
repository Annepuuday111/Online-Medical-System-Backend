package com.klef.medical_system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.medical_system.exception.ResourceNotFoundException;
import com.klef.medical_system.model.Doctor;
import com.klef.medical_system.repository.DoctorRepository;
import com.klef.medical_system.service.DoctorService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        try {
            Doctor savedDoctor = doctorRepository.save(doctor);
            return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not exist with id :" + id));
        return ResponseEntity.ok(doctor);
    }

    @GetMapping("/doctorId/{doctorId}")
    public ResponseEntity<Doctor> getDoctorByDoctorId(@PathVariable String doctorId) {
        Doctor doctor = doctorRepository.findByDoctorId(doctorId);
        if (doctor != null) {
            return ResponseEntity.ok(doctor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/details")
    public ResponseEntity<Doctor> getDoctorByEmail(@RequestParam String email) {
        Optional<Doctor> optionalDoctor = doctorRepository.findByEmail(email);
        if (optionalDoctor.isPresent()) {
            return ResponseEntity.ok(optionalDoctor.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable int id, @RequestBody Doctor doctorDetails) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not exist with id :" + id));

        doctor.setFullName(doctorDetails.getFullName());
        doctor.setGender(doctorDetails.getGender());
        doctor.setDob(doctorDetails.getDob());
        doctor.setQualification(doctorDetails.getQualification());
        doctor.setSpecialist(doctorDetails.getSpecialist());
        doctor.setEmail(doctorDetails.getEmail());
        doctor.setMobNo(doctorDetails.getMobNo());
        doctor.setPassword(doctorDetails.getPassword());

        Doctor updatedDoctor = doctorRepository.save(doctor);
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDoctor(@PathVariable int id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not exist with id :" + id));

        doctorRepository.delete(doctor);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginDoctor(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        if (doctorService.loginDoctor(email, password)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/counts")
    public ResponseEntity<Map<String, Integer>> getDoctorCount() {
        Map<String, Integer> countMap = new HashMap<>();
        countMap.put("totalDoctors", (int) doctorRepository.count());
        return ResponseEntity.ok(countMap);
    }
}
