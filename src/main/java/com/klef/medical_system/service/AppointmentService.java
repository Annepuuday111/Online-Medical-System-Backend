package com.klef.medical_system.service;

import com.klef.medical_system.exception.ResourceNotFoundException;
import com.klef.medical_system.model.Appointment;
import com.klef.medical_system.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment getAppointmentById(int id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
    }

    public Appointment updateAppointment(int id, Appointment appointmentDetails) {
        Appointment appointment = getAppointmentById(id);
        
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

        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(int id) {
        Appointment appointment = getAppointmentById(id);
        appointmentRepository.delete(appointment);
    }
}
