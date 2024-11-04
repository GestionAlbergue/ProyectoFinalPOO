/**
 * Universidad del Valle de Guatemala - Segundo Semestre 2024
 * Programación Orientada a Objetos (POO)
 * Proyecto Final 2
 * 
 * Clase Resource
 * 
 * La clase Resource representa un recurso con un nombre, cantidad disponible y descripción.
 * También permite la actualización de la cantidad y la generación de alertas de reabastecimiento.
 * 
 * @author Adriana Martinez 
 * Fecha de creación: 18/09/2024 
 * Última modificación: 03/11/2024
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Resource {
    private String resourceName;  // Nombre del recurso
    private int quantity;         // Cantidad del recurso disponible
    private String description;   // Descripción del recurso
    private int threshold = 10;   // Alerta de Recurso Bajo

    /**
     * Constructor para inicializar un recurso con su nombre, cantidad y descripción.
     *
     * @param resourceName El nombre del recurso.
     * @param quantity La cantidad disponible del recurso.
     * @param description Una breve descripción del recurso.
     */
    public Resource(String resourceName, int quantity, String description) {
        this.resourceName = resourceName;
        this.quantity = quantity;
        this.description = description;
    }

    /**
     * Devuelve el nombre del recurso.
     *
     * @return El nombre del recurso.
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * Devuelve la cantidad disponible del recurso.
     *
     * @return La cantidad disponible del recurso.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Devuelve la descripción del recurso.
     *
     * @return La descripción del recurso.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Devuelve el threshold para los recursos.
     *
     * @return Threshold para los recursos
     */
    public int getThreshold() {
        return threshold;
    }

    /**
     * Actualiza la cantidad disponible del recurso.
     * Después de actualizar la cantidad, verifica si es necesario generar una alerta de reabastecimiento.
     *
     * @param quantity La nueva cantidad del recurso.
     */
    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Actualiza el threshold de los recursos
     *
     * @param threshold El nuevo threshold.
     */
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    /**
     * Verifica si es necesario generar una alerta de reabastecimiento basada en un umbral de cantidad.
     *
     * @return Un mensaje de alerta si la cantidad es menor o igual al umbral, o null si no se necesita alerta.
     */
    public String checkAlert() {
        if (this.quantity <= this.threshold) {
            return "==========================================\n" +
                   "===               ALERTA               ===\n" + 
                   "= Es necesario reabastecer " + this.resourceName + "\n" +
                   "= Cantidad actual: " + this.quantity + "\n" +
                   "==========================================\n";
        }
        return null; // No hay alerta
    }

    /**
     * Devuelve el mensaje de alerta si la cantidad del recurso es baja.
     *
     * @return Un mensaje de alerta o null si no es necesario.
     */
    public String getAlertMessage() {
        return checkAlert();
    }

    /**
     * Método para convertir un objeto Resource en una línea CSV.
     * @return La representación en formato CSV del objeto Resource.
     */
    public String toCSV() {
        return resourceName + "," + quantity + "," + description;
    }

    /**
     * Método para cargar un objeto Resource desde una línea CSV.
     * @param csvLine La línea CSV que contiene la información del Resource.
     * @return El objeto Resource creado a partir de la línea CSV.
     */
    public static Resource fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        String resourceName = fields[0];
        int quantity = Integer.parseInt(fields[1]);
        String description = fields[2];

        return new Resource(resourceName, quantity, description);
    }

    /**
     * Método para guardar la lista de recursos a un archivo CSV.
     * @param resources Lista de recursos a guardar en formato CSV.
     * @param filePath Ruta del archivo donde se guardará la información.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public static void saveToCSV(List<Resource> resources, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Resource resource : resources) {
                writer.write(resource.toCSV());
                writer.newLine();
            }
        }
    }

    /**
     * Método para cargar la lista de recursos desde un archivo CSV.
     * @param filePath Ruta del archivo CSV desde donde se cargarán los recursos.
     * @return Lista de recursos cargados desde el archivo CSV.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public static List<Resource> loadFromCSV(String filePath) throws IOException {
        List<Resource> resources = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                resources.add(Resource.fromCSV(line));
            }
        }
        return resources;
    }
    /**
     * Filtra los recursos actualizados en el último año.
     *
     * @param resources Lista de recursos a filtrar.
     * @return Una lista de recursos actualizados en el último año.
     */
    public static List<Resource> filterUpdatedInLastYear(List<Resource> resources) {
        List<Resource> filteredList = new ArrayList<>();
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        for (Resource resource : resources) {
            if (resource.getLastUpdated().isAfter(oneYearAgo)) {
                filteredList.add(resource);
            }
        }
        return filteredList;
    }
     /**
     * Filtra los recursos actualizados en el último mes.
     *
     * @param resources Lista de recursos a filtrar.
     * @return Una lista de recursos actualizados en el último mes.
     */
    public static List<Resource> filterUpdatedInLastMonth(List<Resource> resources) {
        List<Resource> filteredList = new ArrayList<>();
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        for (Resource resource : resources) {
            if (resource.getLastUpdated().isAfter(oneMonthAgo)) {
                filteredList.add(resource);
            }
        }
        return filteredList;
    }
    /**
     * Filtra los recursos actualizados en la última semana.
     *
     * @param resources Lista de recursos a filtrar.
     * @return Una lista de recursos actualizados en la última semana.
     */
    public static List<Resource> filterUpdatedInLastWeek(List<Resource> resources) {
        List<Resource> filteredList = new ArrayList<>();
        LocalDate oneWeekAgo = LocalDate.now().minusWeeks(1);
        for (Resource resource : resources) {
            if (resource.getLastUpdated().isAfter(oneWeekAgo)) {
                filteredList.add(resource);
            }
        }
        return filteredList;
    }
}
