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
 * Última modificación: 18/09/2024
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
     * Muestra el historial médico completo del animal en la consola.
     */
    public void displayHistory() {
        // Completar lógica
    }
}
