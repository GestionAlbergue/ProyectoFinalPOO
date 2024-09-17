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
    private List<Resource> resources;

    // Constructor para inicializar las listas de animales, voluntarios y recursos
    public Report(List<Animal> animals, List<Volunteer> volunteers, List<Resource> resources) {
        this.animals = animals;
        this.volunteers = volunteers;
        this.resources = resources;
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
     */
    public void generateAnimalStats() {
        System.out.println("Generando estadísticas de animales...");
    }

    /**
     * Genera un informe sobre los voluntarios del albergue.
     */
    public void generateVolunteerReport() {
        System.out.println("Generando informe de voluntarios...");
    }

    /**
     * Genera un informe sobre los recursos del albergue.
     */
    public void generateResourceReport() {
        System.out.println("Generando informe de recursos...");
    }
}
