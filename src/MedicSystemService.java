import java.time.LocalDateTime;
import java.util.List;
import models.*;
import repositories.*;
import interfaces.*;
public class MedicSystemService {

    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final SpecialityRepository specialityRepository;
    private final AuthenticationService authService;

    public MedicSystemService(
        UserRepository userRepository,
        AppointmentRepository appointmentRepository,
        PatientRepository patientRepository,
        DoctorRepository doctorRepository,
        SpecialityRepository specialityRepository,
        AuthenticationService authService
    ) {
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.specialityRepository = specialityRepository;
        this.authService = authService;
    }

    // SRP: Este método se encarga solo del registro de usuarios
    public boolean registerUser(String fullName, int id, String password, int age, String email, UserRole role) {
        User user = new User(id, fullName, age, email, role);
        user.setPassword(password);
        return userRepository.save(user);
    }

    // SRP: Este método delega la autenticación al servicio correspondiente
    public boolean loginUser(String email, String password) {
        return authService.authenticate(email, password);
    }

    // OCP: Si se quiere cambiar la lógica de agendamiento, se puede extender sin modificar este método
    public boolean scheduleAppointment(Patient patient, Doctor doctor, LocalDateTime dateTime) {
        Appointment appointment = new Appointment(patient, doctor, dateTime);
        return appointmentRepository.save(appointment);
    }

    // SRP: Cada método tiene una única responsabilidad
    public List<Appointment> viewPatientHistory(Patient patient) {
        return appointmentRepository.findByPatient(patient);
    }

    public List<Appointment> viewDoctorHistory(Doctor doctor) {
        return appointmentRepository.findByDoctor(doctor);
    }

    public List<Appointment> viewPendingAppointments(User user) {
        return appointmentRepository.findPendingByUser(user);
    }
}