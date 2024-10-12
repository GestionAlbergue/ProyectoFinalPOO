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
 * Última modificación: 12/10/2024
 */

public class MedicalRecord {
    private String date;          // Fecha del registro
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
    public MedicalRecord(String date, String description, String treatment, String veterinarian) {
        this.date = date;
        this.description = description;
        this.treatment = treatment;
        this.veterinarian = veterinarian;
    }

    /**
     * Devuelve la fecha del registro médico.
     *
     * @return La fecha del registro.
     */
    public String getDate() {
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
     * Devuelve el registro médico completo en formato de texto.
     *
     * @return Una cadena que contiene toda la información del registro médico: fecha, descripción, tratamiento y veterinario.
     */
    public String getFullMedicalRecord() {
        return "Fecha: " + date +
               "\nDescripción: " + description +
               "\nTratamiento: " + treatment +
               "\nVeterinario: " + veterinarian +
               "\n-------------------------------------------------";

    }
}
