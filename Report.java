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
 * Última modificación: 03/11/2024
 */

import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
 * Genera un informe detallado de adoptantes ordenado por cantidad de adopciones.
 * Incluye filtros por fecha y estadísticas adicionales.
 * 
 * @param startDate Fecha inicial para el filtrado (opcional, puede ser null)
 * @param endDate Fecha final para el filtrado (opcional, puede ser null)
 * @return String con el informe detallado
 */
public String generateTopAdoptersReport(LocalDate startDate, LocalDate endDate) {
    StringBuilder report = new StringBuilder();
    report.append("\n=== Informe de Adoptantes por Cantidad de Adopciones ===\n\n");

    // Crear una lista temporal de adoptantes con sus adopciones en el período
    List<AdoptionCandidate> filteredAdopters = new ArrayList<>(adoptionCandidates);
    
    // Ordenar adoptantes por cantidad de animales adoptados (de mayor a menor)
    Collections.sort(filteredAdopters, new Comparator<AdoptionCandidate>() {
        @Override
        public int compare(AdoptionCandidate a1, AdoptionCandidate a2) {
            int adopciones1 = getAdoptionCountInPeriod(a1, startDate, endDate);
            int adopciones2 = getAdoptionCountInPeriod(a2, startDate, endDate);
            return Integer.compare(adopciones2, adopciones1);
        }
    });

    // Estadísticas generales
    int totalAdoptions = 0;
    int totalDangerousAnimals = 0;
    
    // Generar el informe detallado
    for (AdoptionCandidate adopter : filteredAdopters) {
        List<Animal> adoptedAnimals = getAdoptedAnimalsInPeriod(adopter, startDate, endDate);
        int adoptionCount = adoptedAnimals.size();
        
        if (adoptionCount > 0) {
            report.append("ID Adoptante: ").append(adopter.getId()).append("\n");
            report.append("Nombre: ").append(adopter.getName()).append("\n");
            report.append("Contacto: ").append(adopter.getContactInfo()).append("\n");
            report.append("Total de adopciones: ").append(adoptionCount).append("\n");
            
            // Detalles de los animales adoptados
            report.append("Animales adoptados:\n");
            int dangerousCount = 0;
            
            for (Animal animal : adoptedAnimals) {
                Adoption adoption = findAdoptionByAnimal(animal);
                if (adoption != null) {
                    report.append("  - ").append(animal.getName())
                          .append(" (").append(animal.getBreed()).append(")")
                          .append(", Adoptado el: ").append(adoption.getAdoptionDate())
                          .append(", Peligrosidad: ").append(animal.getDangerLevelDescription())
                          .append("\n");
                    
                    if (animal.getDangerLevel()) {
                        dangerousCount++;
                        totalDangerousAnimals++;
                    }
                }
            }
            
            // Estadísticas del adoptante
            report.append("Animales peligrosos adoptados: ").append(dangerousCount)
                  .append(" (").append(String.format("%.1f", (dangerousCount * 100.0 / adoptionCount)))
                  .append("%)\n");
            
            report.append("Voluntario asignado: ")
                  .append(adopter.getVolunteer().getName())
                  .append("\n");
            
            report.append("----------------------------------------\n");
            totalAdoptions += adoptionCount;
        }
    }
    
    // Estadísticas globales
    report.append("\n=== Estadísticas Globales ===\n");
    report.append("Total de adoptantes activos: ").append(filteredAdopters.size()).append("\n");
    report.append("Total de adopciones: ").append(totalAdoptions).append("\n");
    if (totalAdoptions > 0) {
        report.append("Porcentaje de animales peligrosos adoptados: ")
              .append(String.format("%.1f", (totalDangerousAnimals * 100.0 / totalAdoptions)))
              .append("%\n");
    }
    
    return report.toString();
}

/**
 * Método auxiliar para obtener el número de adopciones de un adoptante en un período específico
 */
private int getAdoptionCountInPeriod(AdoptionCandidate adopter, LocalDate startDate, LocalDate endDate) {
    if (startDate == null || endDate == null) {
        return adopter.getAnimals().size();
    }
    
    return (int) adoptions.stream()
        .filter(adoption -> adoption.getAdopter().equals(adopter))
        .filter(adoption -> {
            LocalDate adoptionDate = adoption.getAdoptionDate();
            return !adoptionDate.isBefore(startDate) && !adoptionDate.isAfter(endDate);
        })
        .count();
}

/**
 * Método auxiliar para obtener los animales adoptados en un período específico
 */
private List<Animal> getAdoptedAnimalsInPeriod(AdoptionCandidate adopter, LocalDate startDate, LocalDate endDate) {
    if (startDate == null || endDate == null) {
        return adopter.getAnimals();
    }
    
    return adoptions.stream()
        .filter(adoption -> adoption.getAdopter().equals(adopter))
        .filter(adoption -> {
            LocalDate adoptionDate = adoption.getAdoptionDate();
            return !adoptionDate.isBefore(startDate) && !adoptionDate.isAfter(endDate);
        })
        .map(Adoption::getAnimal)
        .collect(Collectors.toList());
}

    /**
 * Método para filtrar adopciones por rango de fechas.
 * @param startDate Fecha de inicio del período
 * @param endDate Fecha de fin del período
 * @return Lista de adopciones dentro del rango de fechas especificado
 */
private List<Adoption> filterAdoptionsByDateRange(LocalDate startDate, LocalDate endDate) {
    List<Adoption> filteredAdoptions = new ArrayList<>();
    for (Adoption adoption : adoptions) {
        LocalDate adoptionDate = adoption.getAdoptionDate();
        if ((adoptionDate.isEqual(startDate) || adoptionDate.isAfter(startDate)) && 
            (adoptionDate.isEqual(endDate) || adoptionDate.isBefore(endDate))) {
            filteredAdoptions.add(adoption);
        }
    }
    return filteredAdoptions;
}

/**
 * Genera un informe de adopciones para un período específico.
 * @param startDate Fecha de inicio del período
 * @param endDate Fecha de fin del período
 * @return String con el informe de adopciones del período
 */
public String generateAdoptionReportByDate(LocalDate startDate, LocalDate endDate) {
    List<Adoption> filteredAdoptions = filterAdoptionsByDateRange(startDate, endDate);
    StringBuilder report = new StringBuilder();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    report.append("\n=== Informe de Adopciones ===\n")
          .append("Período: ").append(startDate.format(formatter))
          .append(" - ").append(endDate.format(formatter)).append("\n")
          .append("Total de adopciones en el período: ").append(filteredAdoptions.size()).append("\n")
          .append("================================\n");

    if (filteredAdoptions.isEmpty()) {
        report.append("No se registraron adopciones en este período.\n");
    } else {
        for (Adoption adoption : filteredAdoptions) {
            Animal animal = adoption.getAnimal();
            AdoptionCandidate adopter = adoption.getAdopter();
            report.append("Fecha: ").append(adoption.getAdoptionDate().format(formatter)).append("\n")
                  .append("Animal: ").append(animal.getName())
                  .append(" (").append(animal.getBreed()).append(")\n")
                  .append("Adoptante: ").append(adopter.getName())
                  .append(", Contacto: ").append(adopter.getContactInfo()).append("\n")
                  .append("--------------------------------\n");
        }
    }

    return report.toString();
}

/**
 * Genera estadísticas mensuales de adopciones.
 * @param year Año para generar estadísticas
 * @param month Mes para generar estadísticas
 * @return String con las estadísticas mensuales
 */
public String generateMonthlyAdoptionStats(int year, int month) {
    LocalDate startDate = LocalDate.of(year, month, 1);
    LocalDate endDate = startDate.plusMonths(1).minusDays(1);
    List<Adoption> monthAdoptions = filterAdoptionsByDateRange(startDate, endDate);
    
    StringBuilder stats = new StringBuilder();
    stats.append("\n=== Estadísticas de Adopciones ===\n")
         .append("Mes: ").append(startDate.getMonth()).append(" ").append(year).append("\n")
         .append("Total de adopciones: ").append(monthAdoptions.size()).append("\n");

    // Estadísticas adicionales
    long dangerousAnimals = monthAdoptions.stream()
        .filter(a -> a.getAnimal().getDangerLevel())
        .count();

    stats.append("Animales peligrosos adoptados: ").append(dangerousAnimals).append("\n")
         .append("Porcentaje de animales peligrosos: ")
         .append(monthAdoptions.isEmpty() ? 0 : (dangerousAnimals * 100.0 / monthAdoptions.size()))
         .append("%\n");

    return stats.toString();
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
    
        boolean hasIncompleteTasks = false; // Bandera para verificar si hay tareas sin completar
    
        // Agregar detalles de cada tarea no completada
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                hasIncompleteTasks = true; // Se encontró al menos una tarea sin completar
                stats.append(task).append("\n");
                stats.append("......................................").append("\n");
            }
        }
    
        // Si no se encontraron tareas sin completar, mostrar un mensaje
        if (!hasIncompleteTasks) {
            stats.append("No se encuentran tareas sin Completar.\n");
        }
    
        return stats.toString(); // Retornar el reporte como String
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
        stats.append("\n");
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
            report.append("\n");
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
