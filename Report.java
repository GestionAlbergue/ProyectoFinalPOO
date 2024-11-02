/**
 * Universidad del Valle 
 * Programación Orientada a Objetos
 * 
 * Clase Report
 * 
 * La clase Report se encarga de generar informes sobre el albergue, 
 * incluyendo adopciones, estadísticas de animales, voluntarios y recursos.
 * 
 * @author Marjori Flores
 * Fecha de creación: 13/09/2024
 * Última modificación: 2/11/2024
 */

import java.util.List;
import java.util.Comparator;
import java.util.Collections;

public class Report {
    private List<Animal> animals;
    private List<Volunteer> volunteers;
    private List<Task> tasks;  // Añadido para manejar tareas
    private List<Resource> resources;
    private List<AdoptionCandidate> adoptionCandidates;
    private List<Adoption> adoptions;

    /**
     * Constructor que inicializa un informe con listas de animales, voluntarios, recursos, 
     * tareas y candidatos de adopción. Este informe agrupa toda la información relevante
     * para el manejo del refugio.
     *
     * @param animals            La lista de animales presentes en el refugio.
     * @param volunteers         La lista de voluntarios que colaboran en el refugio.
     * @param resources          La lista de recursos disponibles en el refugio.
     * @param tasks              La lista de tareas que se deben realizar en el refugio.
     * @param adoptionCandidates La lista de candidatos que están en proceso de adopción.
     */
    public Report(List<Animal> animals, List<Volunteer> volunteers, List<Resource> resources, List<Task> tasks, List<AdoptionCandidate> adoptionCandidates, List<Adoption> adoptions) {
        this.animals = animals;
        this.volunteers = volunteers;
        this.resources = resources;
        this.tasks = tasks;
        this.adoptionCandidates = adoptionCandidates;
        this.adoptions = adoptions;
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
    
        // Recorrer la lista de animales
        for (Animal animal : animals) {
            if (animal.isAdopted()) {  // Verificar si el animal está adoptado
                hasAdoptions = true;
                Adoption adoption = findAdoptionByAnimal(animal);  // Encontrar la adopción del animal
                if (adoption != null) {
                    AdoptionCandidate adopter = adoption.getAdopter();  // Obtener el adoptante
                    report.append("Animal: ").append(animal.getName())
                        .append(", Raza: ").append(animal.getBreed())
                        .append(" | Adoptante: ").append(adopter.getName())
                        .append(", Contacto: ").append(adopter.getContactInfo())
                        .append(", Fecha de Adopción: ").append(adoption.getAdoptionDate())  // Agregar fecha de adopción
                        .append("\n")
                        .append("=================================\n");
                } else {
                    report.append("Animal: ").append(animal.getName())
                        .append(", Raza: ").append(animal.getBreed())
                        .append(" | Adoptante: Desconocido\n")
                        .append("=================================\n");
                }
            }
        }
        // Si no hay adopciones, agregar un mensaje adecuado
        if (!hasAdoptions) {
            report.append("No se han registrado adopciones hasta el momento.\n");
        }
    
        return report.toString();  // Retornar el reporte como String
    }

    /**
     * Genera un informe sobre los adoptantes registrados en el albergue.
     * 
     * @return Un String que contiene el informe de los adoptantes.
     */
    public String generateAdoptersReport() {
        StringBuilder report = new StringBuilder();  // Usar StringBuilder para construir el informe
    
        // Agregar la cantidad de adoptantes registrados
        report.append("=== Informe de Adoptantes Registrados ===\n");
        report.append("Cantidad de adoptantes: ").append(adoptionCandidates.size()).append("\n");
        report.append("=========================================\n");
    
        // Verificar si hay adoptantes registrados
        if (adoptionCandidates.isEmpty()) {
            report.append("No se han registrado adoptantes.\n");
        } else {
            // Detallar cada adoptante usando su método toString()
            for (AdoptionCandidate candidate : adoptionCandidates) {
                report.append(candidate.toString()).append("\n");  // Añadir el detalle del adoptante
                report.append("=========================================\n");
            }
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
        
        // Verificar la cantidad de voluntarios
        int volunteerCount = volunteers.size();
        report.append("Total de voluntarios registrados: ").append(volunteerCount).append("\n");
        
        // Verificar si hay voluntarios
        if (volunteerCount == 0) {
            report.append("No hay voluntarios registrados.\n");
        } else {
            report.append("\n")
                  .append("------------ DETALLES ------------").append("\n");
            // Recorrer la lista de voluntarios e imprimir sus detalles usando toString()
            for (Volunteer volunteer : volunteers) {
                report.append(volunteer.toString()).append("\n");
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
                report.append(task).append("\n")
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

    /**
     * Genera un informe sobre las tareas no completas en el albergue.
     * 
     * @return Un {@code String} que contiene el informe de las tareas NO completadas en el albergue.
     */
    public String generateNoCompleteTaskReport() {
        StringBuilder stats = new StringBuilder();
        stats.append("\n")
             .append("====== TAREAS NO COMPLETADAS ======\n");
       
        // Agregar detalles de cada tarea
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                stats.append(task).append("\n");
                stats.append("......................................").append("\n");
            }
        }
        
        return stats.toString();  // Retornar el reporte como String
    }

    /**
     * Genera un informe sobre los animales de acuerdo a su nivel de peligrosidad.
     * 
     * @return Un {@code String} que contiene el informe de animales peligrosos o no.
     */
    public String generateDangerLevelReport() {
        int totalAnimals = animals.size();
        int dangerAnimals = 0;
        int noDangerAnimals = 0;
        
        // Recorrer la lista de animales para contar peligrosos y no peligrosos.
        for (Animal animal : animals) {
            if (animal.getDangerLevel()) {
                dangerAnimals++;
            } else {
                noDangerAnimals++;
            }
        }
    
        // Construir el reporte como un String
        StringBuilder stats = new StringBuilder();
        stats.append("\n")
             .append("====== Informe de Animales según Peligrosidad ======\n")
             .append("Total de animales: ").append(totalAnimals).append("\n")
             .append("Animales Peligrosos: ").append(dangerAnimals).append("\n")
             .append("Animales No Peligrosos: ").append(noDangerAnimals).append("\n")
             .append("=================================")
             .append("\n");
    
        // Agregar detalles de cada animal
        stats.append("=== Animales Peligros: ===\n");
        for (Animal animal : animals) {
            if (animal.getDangerLevel()) {
                stats.append(animal).append("\n");
            }
        }
        stats.append("=== Animales No Peligros: ===\n");
        for (Animal animal : animals) {
            if (!animal.getDangerLevel()) {
                stats.append(animal).append("\n");
            }
        }
        return stats.toString();  // Retornar el reporte como String
    }

    /**
     * Genera un informe sobre los 10 Voluntarios.
     * 
     * @return Un {@code String} que contiene el informe de los 10 voluntarios con mayor cantidad de horas.
     */
    public String generateTop10VolunteerReport() {
        StringBuilder report = new StringBuilder();
        report.append("\n")
              .append("====== Informe de Top 10 Voluntarios ======\n");
    
        int volunteerCount = volunteers.size();
    
        if (volunteerCount == 0) {
            report.append("====================================\n");
            report.append("== No hay voluntarios registrados ==\n");
            report.append("====================================\n");
        } else {
            report.append("\n");
            
            // Ordenar los voluntarios en base a las horas trabajadas de mayor a menor
            volunteers.sort(Comparator.comparingInt(Volunteer::getHoursWorked).reversed());
    
            // Limitar el número de voluntarios a 10 o menos si no hay suficientes
            int topVolunteersCount = Math.min(10, volunteerCount);
            for (int i = 0; i < topVolunteersCount; i++) {
                report.append((i + 1) + ".\n");
                report.append(volunteers.get(i).toString()).append("\n");
            }
        }
        return report.toString();
    }

    /** 
     * Genera un informe sobre los recursos con stock menos del threshold
     * 
     * @return Un {@code String} que contiene el informe de los recursos de bajo del threshold
     */
    public String generateLowResourceReport() {
        StringBuilder report = new StringBuilder();
        report.append("\n")
              .append("====== Informe de Recursos Bajos en Cantidad ======\n");
    
        // Filtrar los recursos con cantidad menor a 10
        List<Resource> lowStockResources = resources.stream()
                                                    .filter(resource -> resource.getQuantity() < 10)
                                                    .toList();
    
        // Verificar si hay recursos con menos de 10 unidades
        if (lowStockResources.isEmpty()) {  
            report.append("=====================================\n");
            report.append("== No hay recursos con menos de 10 ==\n"); 
            report.append("==        unidades en stock.       ==\n");
            report.append("=====================================\n");
        } else {
            report.append("Recursos con menos de 10 unidades en stock:\n");
            for (Resource resource : lowStockResources) {
                report.append("Recurso: ").append(resource.getResourceName()).append("\n");
                report.append("Cantidad: ").append(resource.getQuantity()).append("\n");
                report.append("Descripción: ").append(resource.getDescription()).append("\n");
                report.append("==============================\n");
            }
        }
    
        return report.toString();
    }

    /**
     * Genera un informe sobre los adoptantes registrados en el albergue, ordenados del que tiene mayor cantidad de adoptantes.
     * 
     * @return Un String que contiene el informe de los adoptantes con más animales adoptados
     */
    public String generateTopAdoptersReport() {
        StringBuilder report = new StringBuilder();
    
        // Agregar la cantidad de adoptantes registrados
        report.append("=== Informe de Adoptantes con más Animales Adoptados ===\n");
        report.append("\n");
    
        // Verificar si hay adoptantes registrados
        if (adoptionCandidates.isEmpty()) {
            report.append("No se han registrado adoptantes.\n");
        } else {
            // Ordenar la lista de adoptantes por la cantidad de animales adoptados (de mayor a menor)
            Collections.sort(adoptionCandidates, new Comparator<AdoptionCandidate>() {
                @Override
                public int compare(AdoptionCandidate a1, AdoptionCandidate a2) {
                    return Integer.compare(a2.getAnimals().size(), a1.getAnimals().size());
                }
            });
    
            // Detallar cada adoptante usando su método toString()
            for (AdoptionCandidate candidate : adoptionCandidates) {
                report.append(candidate.getCantidadAdoptados()).append("\n");
                report.append("=========================================\n");
            }
        }
    
        return report.toString();
    }

    /**
     * Permite buscar el adoptante del animal
     * 
     * @param animal Animal del cual buscamos el adoptante
     * 
     * @return AdoptionCandidate que es el adoptante del animal.
     */
    public AdoptionCandidate findAdopterByAnimal(Animal animal) {
        for (AdoptionCandidate candidate : adoptionCandidates) {
            if (candidate.getAnimals().contains(animal)) {
                return candidate;
            }
        }
        return null;  // Retorna null si no se encuentra el adoptante
    }

    /**
     * Busca una adopción específica asociada a un animal dado.
     * 
     * @param animal El objeto {@code Animal} para el cual se busca la adopción.
     * @return La adopción asociada al animal dado si se encuentra, o {@code null} si no existe adopción para el animal.
     */
    public Adoption findAdoptionByAnimal(Animal animal) {
        for (Adoption adoption : adoptions) {  // Asumiendo que tienes una lista de adopciones
            if (adoption.getAnimal().equals(animal)) {
                return adoption;
            }
        }
        return null;  // Si no se encuentra adopción para el animal
    }
}
