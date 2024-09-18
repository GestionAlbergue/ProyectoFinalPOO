/**
 * Universidad del Valle 
 * Programación Orientada a Objetos
 * 
 * Clase Report
 * 
 * La clase Report se encarga de generar informes sobre el albergue, 
 * incluyendo adopciones, estadísticas de animales, voluntarios y recursos.
 * 
 * Fecha de creación: 15/09/2024
 * Última modificación: 16/09/2024
 * @author Marjori Flores
 */
import java.util.List;

public class Report {

    private List<Animal> animals;
    private List<Volunteer> volunteers;
    private List<Task> tasks;  // Añadido para manejar tareas

    // Constructor para inicializar las listas de animales, voluntarios, recursos y tareas
    public Report(List<Animal> animals, List<Volunteer> volunteers, List<Resource> resources, List<Task> tasks) {
        this.animals = animals;
        this.volunteers = volunteers;
        this.resources = resources;
        this.tasks = tasks;
    }
    /**
     * Genera un informe sobre las adopciones realizadas en el albergue.
     */
     public String generateAdoptionReport() {
        StringBuilder report = new StringBuilder();  // Usar StringBuilder para construir el String del reporte
        report.append("Informe de adopciones:\n");
        
        boolean hasAdoptions = false;  // Para verificar si hay animales adoptados

        // Recorrer la lista de animales
        for (Animal animal : animals) {
            if (animal.isAdopted()) {  // Verificar si el animal está adoptado
                hasAdoptions = true;
                report.append("Nombre: ").append(animal.getName())
                      .append(", Especie: ").append(animal.getBreed())
                      .append("\n")
                      .append("=================================")
                      .append("\n");
            }
        }

        // Si no hay adopciones, agregar un mensaje adecuado
        if (!hasAdoptions) {
            report.append("No se han registrado adopciones hasta el momento.\n");
        }

        return report.toString();  // Retornar el reporte como String
    }
    /**
     * Genera estadísticas sobre los animales del albergue.
     * 
     * @return Un String con el número total de animales, el número de animales adoptados
     * y el número de animales pendientes de adopción.
     */
    public String generateAnimalStats() {
        int totalAnimals = animals.size();
        int adoptedAnimals = 0;
        int pendingAdoptions = 0;
        // Recorrer la lista de animales para contar adoptados y pendientes
        for (Animal animal : animals) {
            if (animal.isAdopted()) {
                adoptedAnimals++;
            } else {
                pendingAdoptions++;
            }
        }
        // Construir el reporte como un String
        StringBuilder stats = new StringBuilder();
        stats.append("Estadísticas de animales:\n")
            .append("Total de animales: ").append(totalAnimals).append("\n")
            .append("Animales adoptados: ").append(adoptedAnimals).append("\n")
            .append("Animales pendientes de adopción: ").append(pendingAdoptions).append("\n");

        return stats.toString();  // Retornar el reporte como String
    }
    /**
     * Genera un informe sobre los voluntarios del albergue.
     * 
     * @return Un String con la información de todos los voluntarios.
     */
    public String generateVolunteerReport() {
        StringBuilder report = new StringBuilder();
        report.append("Informe de voluntarios:\n");
        // Verificar si hay voluntarios
        if (volunteers.isEmpty()) {
            report.append("No hay voluntarios registrados.\n");
        } else {
            // Recorrer la lista de voluntarios e imprimir sus detalles
            for (Volunteer volunteer : volunteers) {
                report.append("ID: ").append(volunteer.getId()).append("\n")
                    .append("Nombre: ").append(volunteer.getName()).append("\n")
                    .append("Informacion de contacto: ").append(volunteer.getContactInfo()).append("\n")
                    .append("=================================\n");
            }
        }
        return report.toString();  // Retornar el informe como String
    }
    /**
     * Genera un informe sobre la tarea.
     * 
     * @return Un String con la información de la tarea.
     */
    public String generateTaskReport() {
        StringBuilder report = new StringBuilder();
        report.append("Informe de tareas:\n");
        // Verificar si hay tareas
        if (tasks.isEmpty()) {
            report.append("No hay tareas registradas.\n");
        } else {
            // Recorrer la lista de tareas e imprimir sus detalles
            for (Task task : tasks) {
                report.append("Nombre: ").append(task.getName()).append("\n")
                      .append("Descripción: ").append(task.getDescription()).append("\n")
                      .append("Completado: ").append(task.isCompleted() ? "Sí" : "No").append("\n")
                      .append("=================================\n");
            }
        }
        return report.toString();  // Retornar el informe como String
    }
    /**
     * Genera un informe sobre los recursos del albergue.
     */
    public void generateResourceReport() {
        System.out.printIn("Generando el informe de recursos...");
    }
}
