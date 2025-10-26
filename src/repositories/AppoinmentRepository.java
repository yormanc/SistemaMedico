package repositories;
import java.util.ArrayList;
import java.util.stream.Collectors;
import enumerations.AppoinmnetStatus;
import models.Doctor;
import interfaces.IRepositoryAppoinment;
import models.Patient;
import models.Appointment;
import java.time.LocalDateTime;  


public class AppoinmentRepository implements IRepositoryAppoinment {
    private final ArrayList<Appointment> appointments;

    // Agregar constructor
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

            // Verificar si el médico está disponible
            boolean doctorIsBooked = appointments.stream()
                .anyMatch(a -> a.getDoctor().equals(appointment.getDoctor()) 
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
            return true;

        } catch (Exception e) {
            throw new RuntimeException("Error al agregar la cita: " + e.getMessage());
        }
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

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la cita: " + e.getMessage());
        }
        return true;
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
                        appointment.getDoctor().getCredentials().getId()==(doctor.getCredentials().getId()))
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
                        appointment.getPatient().getCredentials().getId()==(patient.getCredentials().getId()))
                .collect(Collectors.toCollection(ArrayList::new));

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las citas del doctor: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Appointment> getByStatus(AppoinmnetStatus status) {
       try {
            if (status == null) {
                throw new Exception("El estado no puede ser nulo");
            }

            return appointments.stream()
                .filter(appointment -> appointment.getStatus() != null &&
                        appointment.getStatus().equals(status))
                .collect(Collectors.toCollection(ArrayList::new));

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las citas del doctor: " + e.getMessage());
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

            // Validar que la nueva fecha y hora sean futuras
            LocalDateTime now = LocalDateTime.now();
            if (updatedAppointment.getDateTime().isBefore(now)) {
                throw new Exception("La fecha y hora de la cita deben ser futuras");
            }

            // Verificar disponibilidad del médico (excluyendo la cita actual)
            boolean doctorIsBooked = appointments.stream()
                .filter(a -> a.getAppointmentId() != updatedAppointment.getAppointmentId())
                .anyMatch(a -> a.getDoctor().equals(updatedAppointment.getDoctor()) 
                        && a.getDateTime().equals(updatedAppointment.getDateTime()));

            if (doctorIsBooked) {
                throw new Exception("El médico ya tiene una cita programada en este horario");
            }

            // Verificar disponibilidad del paciente (excluyendo la cita actual)
            boolean patientIsBooked = appointments.stream()
                .filter(a -> a.getAppointmentId() != updatedAppointment.getAppointmentId())
                .anyMatch(a -> a.getPatient().equals(updatedAppointment.getPatient()) 
                        && a.getDateTime().equals(updatedAppointment.getDateTime()));

            if (patientIsBooked) {
                throw new Exception("El paciente ya tiene una cita programada en este horario");
            }

            // Actualizar los datos de la cita
            int index = appointments.indexOf(existingAppointment);
            appointments.set(index, updatedAppointment);

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la cita: " + e.getMessage());
        }
        return true;
    }

    @Override
    public ArrayList<Appointment> getByDoctorAndStatus(Doctor doctor,AppoinmnetStatus status){
        try {
            if (doctor == null || status == null) {
                throw new Exception("El doctor o el estado no puede ser nulo");
            }

            return appointments.stream()
                .filter(appointment -> appointment.getDoctor() != null && 
                        appointment.getDoctor().getCredentials().getId()==(doctor.getCredentials().getId()) &&
                        appointment.getStatus() != null &&
                        appointment.getStatus().equals(status))
                .collect(Collectors.toCollection(ArrayList::new));

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las citas del doctor: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Appointment> getByPatientAndStatus(Patient patient,AppoinmnetStatus status){
        try {
            if (patient == null || status == null) {
                throw new Exception("El paciente o el estado no puede ser nulo");
            }

            return appointments.stream()
                .filter(appointment -> appointment.getPatient() != null && 
                        appointment.getPatient().getCredentials().getId()==(patient.getCredentials().getId()) &&
                        appointment.getStatus() != null &&
                        appointment.getStatus().equals(status))
                .collect(Collectors.toCollection(ArrayList::new));

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las citas del doctor: " + e.getMessage());
        }
    }
        

}
