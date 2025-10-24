package repositories;
import java.util.ArrayList;
import java.util.stream.Collectors;

import interfaces.AppointmentStatus;
import interfaces.Doctor;
import interfaces.IRepositoryAppoinment;
import interfaces.Patient;
import interfaces.User;
import models.Appointment;
import java.time.LocalDateTime;  
import java.util.stream.Collectors;

public class AppoinmentRepository implements IRepositoryAppoinment {
    private ArrayList<Appointment> appointments;

    // Agregar constructor
    public AppoinmentRepository() {
        this.appointments = new ArrayList<Appointment>();
    }

    @Override
    public void add(Appointment appointment) {
        try {
            // Validaciones básicas
            if (appointment == null) {
                throw new Exception("La cita no puede ser nula");
            }
            if (appointment.getPatient() == null || appointment.getMedic() == null) {
                throw new Exception("La cita debe tener un paciente y un médico asignados");
            }
            if (appointment.getDateTime() == null) {
                throw new Exception("La fecha y hora son obligatorias");
            }

            // Validar que la fecha y hora sean futuras
            LocalDateTime now = LocalDateTime.now();
            if (appointment.getDateTime().isBefore(now)) {
                throw new Exception("La fecha y hora de la cita deben ser futuras");
            }

            // Verificar si el médico está disponible
            boolean doctorIsBooked = appointments.stream()
                .anyMatch(a -> a.getMedic().equals(appointment.getMedic()) 
                        && a.getDateTime().equals(appointment.getDateTime()));

            if (doctorIsBooked) {
                throw new Exception("El médico ya tiene una cita programada en este horario");
            }

            // Verificar si el paciente ya tiene cita en ese horario
            boolean patientIsBooked = appointments.stream()
                .anyMatch(a -> a.getPatient().equals(appointment.getPatient()) 
                        && a.getDateTime().equals(appointment.getDateTime()));

            if (patientIsBooked) {
                throw new Exception("El paciente ya tiene una cita programada en este horario");
            }

            // Si todas las validaciones pasan, agregar la cita
            appointments.add(appointment);

        } catch (Exception e) {
            throw new RuntimeException("Error al agregar la cita: " + e.getMessage());
        }
    }

    @Override
    public void delete(Appointment appointmentToDelete) {
        try {
            if (appointments.isEmpty()) {
                throw new Exception("No hay citas registradas");
            }

            if (appointmentToDelete == null) {
                throw new Exception("La cita a eliminar no puede ser nula");
            }

            boolean wasRemoved = appointments.remove(appointmentToDelete);
            
            if (!wasRemoved) {
                throw new Exception("No se pudo eliminar la cita seleccionada");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la cita: " + e.getMessage());
        }
    }

   @Override
    public ArrayList<Appointment> getAll() {
        return new ArrayList<>(appointments);
    }

   @Override
    public ArrayList<Appointment> getByDoctor(Doctor doctor) {
        try {
            if (doctor == null) {
                throw new Exception("El doctor no puede ser nulo");
            }

            return appointments.stream()
                .filter(appointment -> appointment.getMedic() != null && 
                        appointment.getMedic().getId().equals(doctor.getId()))
                .collect(Collectors.toCollection(ArrayList::new));

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las citas del doctor: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Appointment> getByPatient(Patient patient) {
        try {
            if (patient == null) {
                throw new Exception("El paciente no puede ser nulo");
            }

            return appointments.stream()
                .filter(appointment -> appointment.getPatient() != null && 
                        appointment.getPatient().getId().equals(patient.getId()))
                .collect(Collectors.toCollection(ArrayList::new));

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las citas del doctor: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Appointment> getByStatus(AppointmentStatus status) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Appointment appointment) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ArrayList<Appointment> getByDoctorAndStatus(Doctor doctor, AppointmentStatus status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByDoctorAndStatus'");
    }

    @Override
    public ArrayList<Appointment> getByPatientAndStatus(Patient patient, AppointmentStatus status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByPatientAndStatus'");
    }
        

}
