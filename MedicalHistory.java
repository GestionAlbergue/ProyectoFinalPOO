import java.util.ArrayList;
import java.util.List;

public class MedicalHistory {
    private Animal animal; // Referencia al animal al que pertenece este historial médico
    private List<MedicalRecord> records; // Lista de registros médicos asociados al animal

    // Constructor para inicializar el historial médico para un animal específico
    public MedicalHistory(Animal animal) {
        this.animal = animal;
        this.records = new ArrayList<>(); // Inicializa la lista de registros médicos
    }

    // Añade un nuevo registro médico a la lista records
    public void addRecord(MedicalRecord record) {
        if (record != null) {
            records.add(record);
        }
    }

    // Devuelve la lista de registros médicos
    public List<MedicalRecord> getRecords() {
        return new ArrayList<>(records); // Retorna una copia de la lista para evitar modificaciones externas
    }

    // Muestra el historial médico completo del animal
    public void displayHistory() {
        System.out.println("Historial Médico de " + animal.getName() + ":");
        for (MedicalRecord record : records) {
            System.out.println(record); // Asegúrate de que MedicalRecord tenga un buen método toString()
        }
    }
}
