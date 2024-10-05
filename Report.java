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
 * Última modificación: 18/09/2024
 * @author Marjori Flores
 */
import java.util.List;

public class Report {

    private List<Animal> animals;
    private List<Volunteer> volunteers;
    private List<Task> tasks;  // Añadido para manejar tareas
    private List<Resource> resources;
    private List<AdoptionCandidate> adoptionCandidates;

    // Constructor para inicializar las listas de animales, voluntarios, recursos y tareas
    public Report(List<Animal> animals, List<Volunteer> volunteers, List<Resource> resources, List<Task> tasks, List<AdoptionCandidate> adoptionCandidates) {
        this.animals = animals;
        this.volunteers = volunteers;
        this.resources = resources;
        this.tasks = tasks;
        this.adoptionCandidates = adoptionCandidates;
    }
    /**
     * Genera un informe sobre las adopciones realizadas en el albergue.
     * 
     * @return Un {@code String} que contiene el informe de adopciones realizadas.
     */
    public String generateAdoptionReport() {
        StringBuilder report = new StringBuilder();  // Usar StringBuilder para construir el informe
        report.append("\n")
            .append("=== Informe de Adopciones ===\n");
        
        boolean hasAdoptions = false;  // Para verificar si hay animales adoptados

        // Recorrer la lista de candidatos de adopción
        for (AdoptionCandidate candidate : adoptionCandidates) {
            Animal animal = candidate.getAnimal();
            if (animal.isAdopted()) {  // Verificar si el animal está adoptado
                hasAdoptions = true;
                report.append("Animal: ").append(animal.getName())
                    .append(", Especie: ").append(animal.getBreed())
                    .append(" | Adoptante: ").append(candidate.getName())
                    .append(", Contacto: ").append(candidate.getContactInfo())
                    .append("\n")
                    .append("=================================\n");
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
     * @return Un String con el número total de animales, el número de animales adoptados,
     * el número de animales pendientes de adopción y detalle de todos los animales.
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
        stats.append("\n")
             .append("====== Informe de Animales ======\n")
             .append("Total de animales: ").append(totalAnimals).append("\n")
             .append("Animales adoptados: ").append(adoptedAnimals).append("\n")
             .append("Animales pendientes de adopción: ").append(pendingAdoptions).append("\n")
             .append("=================================")
             .append("\n");
    
        // Agregar detalles de cada animal
        stats.append("Detalle de Animales:\n");
        for (Animal animal : animals) {
            stats.append(animal).append("\n");
        }
        return stats.toString();  // Retornar el reporte como String
    }
    
    /**
     * Genera un informe sobre los voluntarios del albergue.
     * 
     * @return Un String con la información de todos los voluntarios.
     */
    public String generateVolunteerReport() {
        StringBuilder report = new StringBuilder();
        report.append("\n")
              .append("====== Informe de Voluntarios ======\n");
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
        report.append("\n")
              .append("====== Informe de Tareas ======\n");
        // Verificar si hay tareas
        if (tasks.isEmpty()) {
            report.append("No hay tareas registradas.\n");
        } else {
            // Recorrer la lista de tareas e imprimir sus detalles
            for (Task task : tasks) {
                report.append("Nombre: ").append(task.getTaskName()).append("\n")
                      .append("Descripción: ").append(task.getDescription()).append("\n")
                      .append("Completado: ").append(task.isCompleted() ? "Si" : "No").append("\n")
                      .append("=================================\n");
            }
        }
        return report.toString();  // Retornar el informe como String
    }
    
    /**
     * Genera un informe de todos los recursos disponibles.
     * 
     * El informe incluye el nombre del recurso, la cantidad disponible y una descripción
     * de cada recurso en la lista de recursos.
     * 
     * @return Un String que contiene el informe de todos los recursos.
     */
    public String generateResourceReport() {
        StringBuilder report = new StringBuilder(); // Usamos StringBuilder para construir el reporte de forma eficiente
        report.append("\n")
              .append("====== Informe de Recursos ======\n");

        // Recorre la lista de recursos
        for (Resource resource : resources) {
            report.append("Recurso: ").append(resource.getResourceName()).append("\n");
            report.append("Cantidad: ").append(resource.getQuantity()).append("\n");
            report.append("Descripción: ").append(resource.getDescription()).append("\n");
            report.append("==============================\n");
        }

        // Si no hay recursos en la lista
        if (resources.isEmpty()) {
            report.append("No hay recursos disponibles.\n");
        }

        return report.toString(); // Retorna el informe como un String
    }
}
