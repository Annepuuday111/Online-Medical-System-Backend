package com.klef.medical_system.service;

import com.klef.medical_system.model.Doctor;
import com.klef.medical_system.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public boolean loginDoctor(String email, String password) {
        Optional<Doctor> doctor = doctorRepository.findByEmail(email);
        return doctor.isPresent() && doctor.get().getPassword().equals(password);
    }

    public Optional<Doctor> getDoctorByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
}
