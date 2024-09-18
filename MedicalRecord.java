public class MedicalRecord {
    // Atributos de la clase
    private String date;          // Fecha del registro
    private String description;   // Descripción del diagnóstico médico
    private String treatment;     // Tratamiento dado al animal
    private String veterinarian;  // Nombre del veterinario asignado al caso

    // Constructor para inicializar los atributos del registro médico
    public MedicalRecord(String date, String description, String treatment, String veterinarian) {
        this.date = date;
        this.description = description;
        this.treatment = treatment;
        this.veterinarian = veterinarian;
    }

    // Métodos 'getter' para acceder a cada atributo

    // Devuelve la fecha del registro médico
    public String getDate() {
        return date;
    }

    // Devuelve la descripción del diagnóstico
    public String getDescription() {
        return description;
    }

    // Devuelve el tratamiento dado al animal
    public String getTreatment() {
        return treatment;
    }

    // Devuelve el nombre del veterinario que realizó el tratamiento
    public String getVeterinarian() {
        return veterinarian;
    }

    // Método para actualizar la descripción del diagnóstico
    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }

    // Método para actualizar el tratamiento dado
    public void updateTreatment(String newTreatment) {
        this.treatment = newTreatment;
    }

    // Método para actualizar el veterinario asignado
    public void updateVeterinarian(String newVeterinarian) {
        this.veterinarian = newVeterinarian;
    }

    // Método para consultar el historial médico completo
    public String getFullMedicalRecord() {
        return "Fecha: " + date +
               "\nDescripción: " + description +
               "\nTratamiento: " + treatment +
               "\nVeterinario: " + veterinarian;
    }
}
