package models;

public class IManageAppointment {
    public boolean scheduleAppointment(Appointment newAppointment) {
        return true; 
    }
    public boolean cancelAppointment(Appointment appointment) {
        return true; 
    }public boolean modifyAppointment(Appointment appointment) {
        return true; 
    }
    public Appointment searchAppointmentById (int appointmentId) {
        return null; 
    }
    public Appointment[] listAllAppointments() {
        return null; 
    }
}
