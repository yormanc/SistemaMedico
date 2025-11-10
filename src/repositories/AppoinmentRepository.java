package repositories;
import java.util.ArrayList;
import java.util.stream.Collectors;

import enums.AppointmentStatus;
import interfaces.repositories.IRepositoryAppoinment;
import models.Doctor;
import models.Patient;
import models.Appointment;
import java.time.LocalDateTime;  


public class AppoinmentRepository implements IRepositoryAppoinment {
    private final ArrayList<Appointment> appointments;

    public AppoinmentRepository() {
        this.appointments = new ArrayList<Appointment>();
    }

    @Override
    public boolean add(Appointment appointment) {
        try {
            // Validaciones básicas
            if (appointment == null) {
                throw new Exception("La cita no puede ser nula");
            }
            if (appointment.getPatient() == null || appointment.getDoctor() == null) {
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

            // NUEVA VALIDACIÓN: Verificar que no exista una cita con el mismo ID
            boolean idExists = appointments.stream()
                .anyMatch(a -> a.getAppointmentId() == appointment.getAppointmentId());
            
            if (idExists) {
                throw new Exception("Ya existe una cita con el ID: " + appointment.getAppointmentId());
            }

            // Verificar si el médico está disponible en un rango de 1 hora
            boolean doctorIsBooked = appointments.stream()
                .anyMatch(a -> a.getDoctor().getCredentials().getId() == 
                              appointment.getDoctor().getCredentials().getId() 
                        && isTimeConflict(a.getDateTime(), appointment.getDateTime()));

            if (doctorIsBooked) {
                throw new Exception("El médico ya tiene una cita programada cerca de este horario");
            }

            // Verificar si el paciente ya tiene cita en ese rango de tiempo
            boolean patientIsBooked = appointments.stream()
                .anyMatch(a -> a.getPatient().getCredentials().getId() == 
                              appointment.getPatient().getCredentials().getId() 
                        && isTimeConflict(a.getDateTime(), appointment.getDateTime()));

            if (patientIsBooked) {
                throw new Exception("El paciente ya tiene una cita programada cerca de este horario");
            }

            // Si todas las validaciones pasan, agregar la cita
            appointments.add(appointment);
            System.out.println(" Cita agregada exitosamente - ID: " + appointment.getAppointmentId());
            return true;

        } catch (Exception e) {
            System.err.println(" Error al agregar la cita: " + e.getMessage());
            throw new RuntimeException("Error al agregar la cita: " + e.getMessage());
        }
    }

    /**
     * NUEVO MÉTODO: Verifica si dos horarios tienen conflicto
     * Considera un margen de 1 hora antes y después
     */
    private boolean isTimeConflict(LocalDateTime time1, LocalDateTime time2) {
        // Si son exactamente iguales, hay conflicto
        if (time1.equals(time2)) {
            return true;
        }
        
        // Verificar si están dentro de 1 hora de diferencia
        long minutesDiff = Math.abs(java.time.Duration.between(time1, time2).toMinutes());
        return minutesDiff < 60; // Conflicto si hay menos de 60 minutos de diferencia
    }

    @Override
    public boolean remove(Appointment appointmentToremove) {
        try {
            if (appointments.isEmpty()) {
                throw new Exception("No hay citas registradas");
            }

            if (appointmentToremove == null) {
                throw new Exception("La cita a eliminar no puede ser nula");
            }

            boolean wasRemoved = appointments.remove(appointmentToremove);
            
            if (!wasRemoved) {
                throw new Exception("No se pudo eliminar la cita seleccionada");
            }

            System.out.println(" Cita eliminada exitosamente");
            return true;

        } catch (Exception e) {
            System.err.println(" Error al eliminar la cita: " + e.getMessage());
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
                .filter(appointment -> appointment.getDoctor() != null && 
                        appointment.getDoctor().getCredentials().getId() == 
                        doctor.getCredentials().getId())
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
                        appointment.getPatient().getCredentials().getId() == 
                        patient.getCredentials().getId())
                .collect(Collectors.toCollection(ArrayList::new));

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las citas del paciente: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Appointment> getByStatus(AppointmentStatus status) {
       try {
            if (status == null) {
                throw new Exception("El estado no puede ser nulo");
            }

            return appointments.stream()
                .filter(appointment -> appointment.getStatus() != null &&
                        appointment.getStatus().equals(status))
                .collect(Collectors.toCollection(ArrayList::new));

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las citas por estado: " + e.getMessage());
        }
    }

    @Override
    public boolean update(Appointment updatedAppointment) {
        try {
            // Validaciones básicas
            if (updatedAppointment == null) {
                throw new Exception("La cita a actualizar no puede ser nula");
            }

            // Buscar la cita existente
            Appointment existingAppointment = appointments.stream()
                .filter(a -> a.getAppointmentId() == updatedAppointment.getAppointmentId())
                .findFirst()
                .orElseThrow(() -> new Exception("No se encontró la cita a actualizar"));

            // Validar que la nueva fecha y hora sean futuras (solo si cambió la fecha)
            if (!existingAppointment.getDateTime().equals(updatedAppointment.getDateTime())) {
                LocalDateTime now = LocalDateTime.now();
                if (updatedAppointment.getDateTime().isBefore(now)) {
                    throw new Exception("La fecha y hora de la cita deben ser futuras");
                }
            }

            // Verificar disponibilidad del médico (excluyendo la cita actual)
            boolean doctorIsBooked = appointments.stream()
                .filter(a -> a.getAppointmentId() != updatedAppointment.getAppointmentId())
                .anyMatch(a -> a.getDoctor().getCredentials().getId() == 
                              updatedAppointment.getDoctor().getCredentials().getId() 
                        && isTimeConflict(a.getDateTime(), updatedAppointment.getDateTime()));

            if (doctorIsBooked) {
                throw new Exception("El médico ya tiene una cita programada cerca de este horario");
            }

            // Verificar disponibilidad del paciente (excluyendo la cita actual)
            boolean patientIsBooked = appointments.stream()
                .filter(a -> a.getAppointmentId() != updatedAppointment.getAppointmentId())
                .anyMatch(a -> a.getPatient().getCredentials().getId() == 
                              updatedAppointment.getPatient().getCredentials().getId() 
                        && isTimeConflict(a.getDateTime(), updatedAppointment.getDateTime()));

            if (patientIsBooked) {
                throw new Exception("El paciente ya tiene una cita programada cerca de este horario");
            }

            // Actualizar los datos de la cita
            int index = appointments.indexOf(existingAppointment);
            appointments.set(index, updatedAppointment);

            System.out.println(" Cita actualizada exitosamente");
            return true;

        } catch (Exception e) {
            System.err.println(" Error al actualizar la cita: " + e.getMessage());
            throw new RuntimeException("Error al actualizar la cita: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Appointment> getByDoctorAndStatus(Doctor doctor, AppointmentStatus status){
        try {
            if (doctor == null || status == null) {
                throw new Exception("El doctor o el estado no puede ser nulo");
            }

            return appointments.stream()
                .filter(appointment -> appointment.getDoctor() != null && 
                        appointment.getDoctor().getCredentials().getId() == 
                        doctor.getCredentials().getId() &&
                        appointment.getStatus() != null &&
                        appointment.getStatus().equals(status))
                .collect(Collectors.toCollection(ArrayList::new));

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las citas del doctor: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Appointment> getByPatientAndStatus(Patient patient, AppointmentStatus status){
        try {
            if (patient == null || status == null) {
                throw new Exception("El paciente o el estado no puede ser nulo");
            }

            return appointments.stream()
                .filter(appointment -> appointment.getPatient() != null && 
                        appointment.getPatient().getCredentials().getId() == 
                        patient.getCredentials().getId() &&
                        appointment.getStatus() != null &&
                        appointment.getStatus().equals(status))
                .collect(Collectors.toCollection(ArrayList::new));

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las citas del paciente: " + e.getMessage());
        }
    }

    public Appointment searchById(int id) {
        try {
            return appointments.stream()
                .filter(a -> a.getAppointmentId() == id)
                .findFirst()
                .orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la cita por ID: " + e.getMessage());
        }
    }

    /**
     * NUEVO MÉTODO: Obtiene el ID más alto actual
     * Útil para sincronizar el auto-incremento
     */
    public int getMaxAppointmentId() {
        return appointments.stream()
            .mapToInt(Appointment::getAppointmentId)
            .max()
            .orElse(0);
    }

    /**
     * NUEVO MÉTODO: Verifica si existe una cita con un ID específico
     */
    public boolean existsById(int id) {
        return appointments.stream()
            .anyMatch(a -> a.getAppointmentId() == id);
    }
}