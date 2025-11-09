package validators;

import models.User;
import models.Doctor;
import exceptions.ValidationException;

/**
 * Validador para objetos User
 */
public class UserValidator {
    
    /**
     * Valida que un usuario sea válido para registro
     */
    public static void validateForRegistration(User user) throws ValidationException {
        if (user == null) {
            throw ValidationException.nullValue("usuario");
        }
        
        validateFullName(user.getFullName());
        validateAge(user.getAge());
        validateEmail(user.getEmail());
        
        if (user.getCredentials() == null) {
            throw ValidationException.nullValue("credenciales del usuario");
        }
    }
    
    /**
     * Valida que un doctor sea válido para registro
     */
    public static void validateDoctorForRegistration(Doctor doctor) throws ValidationException {
        validateForRegistration(doctor);
        
        if (doctor.getSpeciality() == null) {
            throw ValidationException.nullValue("especialidad del doctor");
        }
    }
    
    /**
     * Valida el nombre completo
     */
    public static void validateFullName(String fullName) throws ValidationException {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw ValidationException.emptyValue("nombre completo");
        }
        
        if (fullName.trim().length() < 3) {
            throw ValidationException.customValidation(
                "nombre completo", 
                "debe tener al menos 3 caracteres"
            );
        }
    }
    
    /**
     * Valida la edad
     */
    public static void validateAge(int age) throws ValidationException {
        if (age < 0 || age > 150) {
            throw ValidationException.invalidAge(age);
        }
    }
    
    /**
     * Valida el email
     */
    public static void validateEmail(String email) throws ValidationException {
        if (email == null || email.trim().isEmpty()) {
            throw ValidationException.emptyValue("email");
        }
        
        // Validación básica de email
        if (!email.contains("@") || !email.contains(".")) {
            throw ValidationException.invalidEmail(email);
        }
    }
}
