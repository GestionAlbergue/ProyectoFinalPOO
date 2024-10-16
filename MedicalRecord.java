/**
 * Universidad del Valle de Guatemala - Segundo Semestre 2024
 * Programación Orientada a Objetos (POO)
 * Proyecto Final
 * 
 * Clase MedicalRecord
 * 
 * La clase MedicalRecord representa un registro médico de un animal.
 * Contiene información sobre la fecha del registro, la descripción del diagnóstico,
 * el tratamiento administrado y el veterinario asignado.
 * 
 * @author Adriana Martinez 
 * Fecha de creación: 18/09/2024 
 * Última modificación: 13/10/2024
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MedicalRecord {
    private LocalDate date;       // Fecha del registro
    private String description;   // Descripción del diagnóstico médico
    private String treatment;     // Tratamiento dado al animal
    private String veterinarian;  // Nombre del veterinario asignado al caso

    /**
     * Constructor para inicializar los atributos del registro médico.
     *
     * @param date La fecha del registro.
     * @param description La descripción del diagnóstico médico.
     * @param treatment El tratamiento administrado al animal.
     * @param veterinarian El nombre del veterinario que realizó el tratamiento.
     */
    public MedicalRecord(LocalDate date, String description, String treatment, String veterinarian) {
        this.date = date;
        this.description = description;
        this.treatment = treatment;
        this.veterinarian = veterinarian;
    }

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Devuelve la fecha del registro médico.
     *
     * @return La fecha del registro.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Devuelve la descripción del diagnóstico médico.
     *
     * @return La descripción del diagnóstico.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Devuelve el tratamiento dado al animal.
     *
     * @return El tratamiento administrado al animal.
     */
    public String getTreatment() {
        return treatment;
    }

    /**
     * Devuelve el nombre del veterinario que realizó el tratamiento.
     *
     * @return El nombre del veterinario.
     */
    public String getVeterinarian() {
        return veterinarian;
    }

    /**
     * Actualiza la descripción del diagnóstico médico.
     *
     * @param newDescription La nueva descripción del diagnóstico.
     */
    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Actualiza el tratamiento dado al animal.
     *
     * @param newTreatment El nuevo tratamiento administrado al animal.
     */
    public void updateTreatment(String newTreatment) {
        this.treatment = newTreatment;
    }

    /**
     * Actualiza el nombre del veterinario asignado al caso.
     *
     * @param newVeterinarian El nuevo nombre del veterinario.
     */
    public void updateVeterinarian(String newVeterinarian) {
        this.veterinarian = newVeterinarian;
    }

    /**
     * Sobreescribe el método toString para dar detalle
     *
     * @return Una cadena que contiene toda la información del registro médico: fecha, descripción, tratamiento y veterinario.
     */
     @Override
    public String toString() {
        return "Fecha: " + date +
               "\nDescripción: " + description +
               "\nTratamiento: " + treatment +
               "\nVeterinario: " + veterinarian +
               "\n-------------------------------------------------";

    }

    /**
     * Método para convertir un objeto MedicalRecord en una línea CSV.
     * @return La representación en formato CSV del objeto MedicalRecord.
     */
    public String toCSV() {
        return date.format(DATE_FORMATTER) + "," + description + "," + treatment + "," + veterinarian;
    }

    /**
     * Método para cargar un objeto MedicalRecord desde una línea CSV.
     * @param csvLine La línea CSV que contiene la información del MedicalRecord.
     * @return El objeto MedicalRecord creado a partir de la línea CSV.
     * @throws DateTimeParseException si la fecha en el CSV no es válida.
     */
    public static MedicalRecord fromCSV(String csvLine) {
        String[] parts = csvLine.split(","); // Separar los campos del registro médico
        if (parts.length != 4) {
            throw new IllegalArgumentException("Línea CSV no válida: " + csvLine);
        }
    
        LocalDate date = LocalDate.parse(parts[0].trim(), DATE_FORMATTER); // Parsear la fecha
        String description = parts[1].trim();
        String treatment = parts[2].trim();
        String veterinarian = parts[3].trim();
    
        return new MedicalRecord(date, description, treatment, veterinarian); // Crear el registro médico
    }
    
}
