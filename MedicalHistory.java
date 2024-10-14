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
 * Última modificación: 13/10/2024
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
     * Añade un nuevo registro médico a la lista de registros cuando viene de CSV
    *
    * @param record El registro médico que se añadirá.
    */
    public void setRecords(List<MedicalRecord> records) {
        this.records = records;
    }

    /**
     * Sobreescribe el método toString del historial médico completo del animal.
    * 
    * @return Un String que representa el historial médico.
    */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (records.isEmpty()) {
            sb.append("=============================================")
            .append("= No hay registros médicos para este animal =")
            .append("=============================================");
        } else {
            sb.append("====================================================").append("\n")
            .append("== Historial Médico para ").append(animal.getName()).append(":\n");
            for (MedicalRecord record : records) {
                sb.append(record).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Método para convertir un objeto MedicalHistory en una línea CSV.
    * @return La representación en formato CSV del objeto MedicalHistory, incluyendo el ID del animal y sus registros médicos.
    */
    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(animal.getId()).append(","); // Agregar el ID del animal
    
        for (int i = 0; i < records.size(); i++) {
            MedicalRecord record = records.get(i);
            sb.append(record.toCSV()); // Agregar cada registro médico
            if (i < records.size() - 1) {
                sb.append(";"); // Separar los registros con un punto y coma
            }
        }
    
        return sb.toString();
    }

    /**
     * Guarda el historial médico en un archivo CSV.
    * @param fileName El nombre del archivo CSV donde se guardará la información.
    * @throws IOException Si ocurre un error al escribir en el archivo.
    */
    public static void saveToCSV(List<MedicalHistory> histories, String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (MedicalHistory history : histories) {
                writer.write(history.toCSV() + "\n"); // Escribir cada historial médico en una línea
            }
        }
    }

    /**
     * Carga un historial médico desde un archivo CSV.
    * @param fileName El nombre del archivo CSV desde donde se cargará la información.
    * @param animals La lista de animales para asociar con el historial cargado.
    * @return Una lista de MedicalHistory con los historiales médicos cargados.
    * @throws IOException Si ocurre un error al leer el archivo.
    */
    public static List<MedicalHistory> loadFromCSV(String fileName, List<Animal> animals) throws IOException {
        List<MedicalHistory> histories = new ArrayList<>(); // Lista de historiales médicos
    
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2); // Separar ID del animal y los registros
                int animalId = Integer.parseInt(parts[0].trim()); // ID del animal
                String recordsPart = parts[1]; // Parte que contiene los registros médicos
    
                // Encontrar el animal correspondiente por ID
                Animal animal = findAnimalById(animalId, animals);
                if (animal == null) {
                    throw new IllegalArgumentException("Animal con ID " + animalId + " no encontrado.");
                }
    
                // Crear un historial médico para el animal
                MedicalHistory history = new MedicalHistory(animal);
    
                // Separar los registros médicos (están separados por ';')
                String[] recordsArray = recordsPart.split(";");
                for (String recordString : recordsArray) {
                    MedicalRecord record = MedicalRecord.fromCSV(recordString); // Convertir cada registro
                    history.addRecord(record); // Añadir registro al historial
                }
    
                histories.add(history); // Añadir historial a la lista
            }
        }
    
        return histories;
    }
    
    /**
     * Método Auxiliar para buscar un objeto Animal en una lista de animales según su ID.
    * 
    * @param id El ID del animal que se desea buscar.
    * @param animals La lista de animales en la que se realizará la búsqueda.
    * @return El objeto Animal cuyo ID coincide con el proporcionado, o null si no se encuentra.
    */
    private static Animal findAnimalById(int id, List<Animal> animals) {
        for (Animal animal : animals) {
            if (animal.getId() == id) {
                return animal; // Retornar el animal si el ID coincide
            }
        }
        return null; // Retornar null si no se encuentra el animal
    }
}