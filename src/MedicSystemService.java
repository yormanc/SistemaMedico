import java.time.LocalDateTime;
import java.util.List;
import enumerations.*;
import models.*;
import repositories.*;
import interfaces.*;
import Services.*;
public class MedicSystemService {

    private final UserRepository userRepository;
    private final AppoinmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final SpecialityRepository specialityRepository;
    private final AuthenticationService authService;
    public User authenticatedUser;

    public MedicSystemService(
        UserRepository userRepository,
        AppoinmentRepository appointmentRepository,
        PatientRepository patientRepository,
        DoctorRepository doctorRepository,
        SpecialityRepository specialityRepository,
        AuthenticationService authService,
        User authenticatedUser

    ) {
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.specialityRepository = specialityRepository;
        this.authService = authService;
        this.authenticatedUser = null;
    }

    // SRP: Este método se encarga solo del registro de usuarios
    public boolean registerPatient(String fullName, int id, String password, int age, String email) {
        Credentials credentials = new Credentials(id, password, UserRole.PATIENT);
        Patient patient = new Patient(fullName, age, email, credentials);
        return patientRepository.add(patient);
    }

    public boolean registerDoctor(String fullName, int id, String password, int age, String email,Speciality speciality) {
        Credentials credentials = new Credentials(id, password, UserRole.DOCTOR);
        Doctor doctor = new Doctor(fullName, age, email, credentials, speciality);
        return doctorRepository.add(doctor);
    }
    public boolean registerAdmin(String fullName, int id, String password, int age, String email) {
        Credentials credentials = new Credentials(id, password, UserRole.ADMIN);
        Patient admin = new Patient(fullName, age, email, credentials);
        return userRepository.add(admin);

    }
    // SRP: Este método delega la autenticación al servicio correspondiente
    public void loginUser(int id, String password) {
       authenticatedUser=authService.login(id, password);
    }

    // OCP: Si se quiere cambiar la lógica de agendamiento, se puede extender sin modificar este método
    public boolean scheduleAppointment(LocalDateTime dateTime, Patient patient, Doctor doctor, String diagnostic) {
        Appointment appointment = new Appointment(dateTime, patient, doctor, diagnostic);
        return appointmentRepository.add(appointment);
    }
    public boolean updateAppointment(Appointment appointment, AppoinmnetStatus status) {
        appointment.setStatus(status);
        return appointmentRepository.update(appointment);
    }
    public boolean removeAppointment(Appointment appointment) {
        return appointmentRepository.remove(appointment);
    }
    public boolean addSpeciality(int id, String name, String description){
        Speciality speciality = new Speciality(id, name, description);
        return specialityRepository.add(speciality);
    }
    public boolean updateSpeciality(Speciality speciality){
        return specialityRepository.update(speciality);
    }
    public boolean removeSpeciality(Speciality speciality){
        return specialityRepository.remove(speciality);
    }
    // SRP: Cada método tiene una única responsabilidad
    public List<Appointment> viewPatientHistory(Patient patient) {
        return appointmentRepository.getByPatient(patient);
    }

    public List<Appointment> viewDoctorHistory(Doctor doctor) {
        return appointmentRepository.getByDoctor(doctor);
    }

    public List<Appointment> viewScheduledAppointmentsDoctor(Doctor doctor, AppoinmnetStatus status) {
        return appointmentRepository.getByDoctorAndStatus(doctor, AppoinmnetStatus.SCHEDULED);
    }
}