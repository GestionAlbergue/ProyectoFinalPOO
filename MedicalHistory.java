/**
 * Universidad del Valle de Guatemala - Segundo Semestre 2024
 * Programación Orientada a Objetos (POO)
 * Proyecto Final
 * 
 * Clase MedicalHistory
 * 
 * Representa el historial médico de un animal.
 * 
 * @author Antony Barrios 
 * Fecha de creación: 18/09/2024 
 * Última modificación: 12/10/2024
 */

import java.util.ArrayList;
import java.util.List;

public class MedicalHistory {
    private Animal animal; // Referencia al animal al que pertenece este historial médico
    private List<MedicalRecord> records; // Lista de registros médicos asociados al animal

    /**
     * Constructor para inicializar el historial médico para un animal específico.
     *
     * @param animal El animal al que pertenece este historial médico.
     */
    public MedicalHistory(Animal animal) {
        this.animal = animal;
        this.records = new ArrayList<>(); // Inicializa la lista de registros médicos
    }

    /**
     * Añade un nuevo registro médico a la lista de registros.
     *
     * @param record El registro médico que se añadirá.
     */
    public void addRecord(MedicalRecord record) {
        if (record != null) {
            records.add(record);
        }
    }

    /**
     * Devuelve la lista de registros médicos asociados a este historial.
     *
     * @return Una lista de registros médicos.
     */
    public List<MedicalRecord> getRecords() {
        return new ArrayList<>(records); // Retorna una copia de la lista para evitar modificaciones externas
    }

    /**
     * Devuelve el animal asociado a este historial médico.
     *
     * @return El animal al que pertenece este historial médico.
     */
    public Animal getAnimal() {
        return animal;
    }


    /**
     * Devuelve el historial médico completo del animal como un String.
     * 
     * @return Un String que representa el historial médico.
     */
    public String displayHistory() {
        StringBuilder sb = new StringBuilder();
        if (records.isEmpty()) {
            sb.append("=============================================")
              .append("= No hay registros médicos para este animal =")
              .append("=============================================");
        } else {
            sb.append(" ==================================================== ")
              .append(" == Historial Médico para ").append(animal.getName()).append(":\n");
            for (MedicalRecord record : records) {
                sb.append(record.getFullMedicalRecord()).append("\n");
            }
        }
        return sb.toString();
    }
}
