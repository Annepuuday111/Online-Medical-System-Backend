package com.klef.medical_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.medical_system.exception.ResourceNotFoundException;
import com.klef.medical_system.model.Appointment;
import com.klef.medical_system.model.Doctor;
import com.klef.medical_system.repository.AppointmentRepository;
import com.klef.medical_system.repository.DoctorRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        try {
            Appointment savedAppointment = appointmentRepository.save(appointment);
            return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable int id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not exist with id :" + id));
        return ResponseEntity.ok(appointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable int id, @RequestBody Appointment appointmentDetails) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not exist with id :" + id));

        appointment.setFullName(appointmentDetails.getFullName());
        appointment.setGender(appointmentDetails.getGender());
        appointment.setAge(appointmentDetails.getAge());
        appointment.setAppoinDate(appointmentDetails.getAppoinDate());
        appointment.setEmail(appointmentDetails.getEmail());
        appointment.setPhNo(appointmentDetails.getPhNo());
        appointment.setDiseases(appointmentDetails.getDiseases());
        appointment.setDoctorId(appointmentDetails.getDoctorId());
        appointment.setAddress(appointmentDetails.getAddress());
        appointment.setStatus(appointmentDetails.getStatus());

        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return ResponseEntity.ok(updatedAppointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAppointment(@PathVariable int id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not exist with id :" + id));

        appointmentRepository.delete(appointment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorId(@PathVariable int doctorId) {
        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);

        if (appointments.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }

        return ResponseEntity.ok(appointments); 
    }
    
    @GetMapping("/counts")
    public ResponseEntity<Map<String, Long>> getAppointmentCounts() {
        long appointmentCount = appointmentRepository.count();

        Map<String, Long> counts = new HashMap<>();
        counts.put("totalAppointments", appointmentCount);

        return ResponseEntity.ok(counts);
    }
}
