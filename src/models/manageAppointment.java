package models;

public class manageAppointment {
    private Appointment[] activeAppointments;
    
    public boolean scheduleAppointment(Appointment newAppointment) {
        activeAppointments.add(newAppointment);
        return true;
    }
    
    public boolean cancelAppointment(int appointmentId) {
        for (int i = 0; i < activeAppointments.length; i++) {
            if (activeAppointments[i].getId() == appointmentId) {
                activeAppointments.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public boolean modifyAppointment(Appointment appointment) {
        for (int i = 0; i < activeAppointments.length; i++) {
            if (activeAppointments[i].getId() == appointment.getId()) {
                activeAppointments[i] = appointment;
                return true;
            }
        }
        return false;
    }
    
    public Appointment searchAppointmentById (int appointmentId) {
        for (Appointment appointment : activeAppointments) {
            if (appointment.getId() == appointmentId) {
                return appointment;
            }
        }
        return null;
    }
    
    public Appointment[] listAllAppointments() {
        return activeAppointments.toArray(new Appointment[0]);
    }
}
