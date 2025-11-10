import javax.swing.*;
import Services.MedicSystemService;
import models.Speciality;

/**
 * Formulario de búsqueda de especialidades
 */
public class FrmSearchSpeciality extends javax.swing.JFrame {

    private final MedicSystemService medicService;
    
    public FrmSearchSpeciality() {
        this.medicService = new MedicSystemService();
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public FrmSearchSpeciality(MedicSystemService medicService) {
        this.medicService = medicService;
        initComponents();
        setLocationRelativeTo(null);
    }

    // ... (código de initComponents igual) ...

    private void jbtnBuscarModificarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String idInput = jtextId.getText().trim();
            
            if (idInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Debe ingresar un ID de especialidad", 
                    "Campo Vacío", 
                    JOptionPane.WARNING_MESSAGE);
                jtextId.requestFocus();
                return;
            }
            
            int id = Integer.parseInt(idInput);
            
            if (id <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "El ID debe ser un número positivo", 
                    "ID Inválido", 
                    JOptionPane.WARNING_MESSAGE);
                jtextId.selectAll();
                jtextId.requestFocus();
                return;
            }
            
            // CORREGIDO: usar findSpecialityById
            Speciality speciality = medicService.findSpecialityById(id);
            
            if (speciality != null) {
                FrmModifySpeciality frmModify = new FrmModifySpeciality(speciality, medicService);
                frmModify.setVisible(true);
                frmModify.setLocationRelativeTo(this); // AGREGADO
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "No se encontró especialidad con ID: " + id, 
                    "No Encontrada", 
                    JOptionPane.INFORMATION_MESSAGE);
                jtextId.selectAll();
                jtextId.requestFocus();
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "El ID debe ser un número válido", 
                "Error de Formato", 
                JOptionPane.ERROR_MESSAGE);
            jtextId.selectAll();
            jtextId.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al buscar: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void jbtnBuscarEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String idInput = jtextId.getText().trim();
            
            if (idInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Debe ingresar un ID de especialidad", 
                    "Campo Vacío", 
                    JOptionPane.WARNING_MESSAGE);
                jtextId.requestFocus();
                return;
            }
            
            int id = Integer.parseInt(idInput);
            
            if (id <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "El ID debe ser un número positivo", 
                    "ID Inválido", 
                    JOptionPane.WARNING_MESSAGE);
                jtextId.selectAll();
                jtextId.requestFocus();
                return;
            }
            
            // CORREGIDO: usar findSpecialityById
            Speciality speciality = medicService.findSpecialityById(id);
            
            if (speciality != null) {
                FrmDeleteSpeciality frmDelete = new FrmDeleteSpeciality(medicService); // CORREGIDO: pasar medicService
                frmDelete.setSpecialityToDelete(speciality);
                frmDelete.setVisible(true);
                frmDelete.setLocationRelativeTo(this); // AGREGADO
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "No se encontró especialidad con ID: " + id, 
                    "No Encontrada", 
                    JOptionPane.INFORMATION_MESSAGE);
                jtextId.selectAll();
                jtextId.requestFocus();
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "El ID debe ser un número válido", 
                "Error de Formato", 
                JOptionPane.ERROR_MESSAGE);
            jtextId.selectAll();
            jtextId.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al buscar: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // ... (resto del código) ...
}