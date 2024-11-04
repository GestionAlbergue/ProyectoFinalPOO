/**
 * Universidad del Valle de Guatemala - Segundo Semestre 2024
 * Programación Orientada a Objetos (POO)
 * Proyecto Final
 * 
 * Clase Volunteer
 * 
 * La clase Volunteer representa a un voluntario en el sistema de gestión de un albergue.
 * Cada voluntario tiene un nombre, información de contacto y un registro de horas trabajadas.
 * 
 * @author Daniela Navas
 * Fecha de creación: 12/09/2024 
 * Última modificación: 13/09/2024
 */

 import java.io.*;
 import java.util.ArrayList;
 import java.util.List;

public class Volunteer {
    private String name,         // Nombre del voluntario
                   contactInfo;  // Información de contacto del voluntario
    private int hoursWorked,     // Cantidad de horas trabajadas por el voluntario
                id;              // ID del voluntario
    private static int idCounter = 1; // ID único ascendiente                 

    /**
     * Constructor de la clase Volunteer.
     * 
     * @param name        El nombre del voluntario.
     * @param contactInfo La información de contacto del voluntario.
     */
    public Volunteer(String name, String contactInfo, int hoursWorked) {
        this.id = idCounter++;              // Asignar ID único y aumentar el contador
        this.name = name;                   // Inicializa el nombre del voluntario
        this.contactInfo = contactInfo;     // Inicializa la información de contacto
        this.hoursWorked = hoursWorked;               // Inicializa las horas trabajadas en 0
    }

    /**
     * Obtiene el nombre del voluntario.
     * 
     * @return El nombre del voluntario.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene la información de contacto del voluntario.
     * 
     * @return La información de contacto del voluntario.
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * Obtiene la cantidad de horas trabajadas por el voluntario.
     * 
     * @return La cantidad de horas trabajadas.
     */
    public int getHoursWorked() {
        return hoursWorked;
    }

    /**
     * Añade horas al registro de horas trabajadas por el voluntario.
     * 
     * @param hours Las horas que se añadirán al total de horas trabajadas.
     */
    public void addHours(int hours) {
        this.hoursWorked += hours;  // Suma las horas trabajadas a las actuales
    }

    /**
     * Obtiene ID el voluntario.
     * 
     * @return ID del Voluntario.
     */
    public int getId() {
        return id;
    }

    /**
     * Sobreescribe el método toString para dar indormación detallada del Voluntario 
     * 
     * @return String de detalles del voluntario.
     */
    @Override
    public String toString() {
        return "-------------------------------------------------------\n" +
            "ID: " + this.id + "\n" +
            "Nombre: " + this.name + "\n" +
            "Información de Contacto: " + this.contactInfo + "\n" +
            "Horas Trabajadas: " + this.hoursWorked + "\n" +
            "-------------------------------------------------------\n";
    }

    /**
     * Convierte un objeto Volunteer en una línea CSV.
     *
     * @return Una cadena que representa el objeto Volunteer en formato CSV, donde los campos están separados por comas.
     */
    public String toCSV() {
        return id + "," + name + "," + contactInfo + "," + hoursWorked;
    }

    /**
     * Carga un objeto Volunteer desde una línea CSV.
     *
     * @param csvLine La línea CSV que contiene los datos del Voluntario, donde los campos están separados por comas.
     * @return Un objeto Voluntario con los atributos cargados desde la línea CSV.
     * @throws NumberFormatException Si los valores numéricos en la línea CSV no pueden ser convertidos correctamente.
     */
    public static Volunteer fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        int id = Integer.parseInt(fields[0]);
        String name = fields[1];
        String contactInfo = fields[2];
        int hoursWorked = Integer.parseInt(fields[3]);

        // Crear el voluntario con el ID ya cargado
        Volunteer volunteer = new Volunteer(name, contactInfo, hoursWorked);
        volunteer.id = id;  // Asignar el ID manualmente
        idCounter = Math.max(idCounter, id + 1);  // Actualizar el contador de ID si es necesario
        return volunteer;
    }

    /**
     * Guarda la lista de Voluntarios en un archivo CSV.
     *
     * @param animals La lista de objetos Voluntario que se desea guardar en el archivo.
     * @param filePath La ruta del archivo donde se guardarán los datos.
     * @throws IOException Si ocurre un error al intentar escribir en el archivo.
     */
    public static void saveToCSV(List<Volunteer> volunteers, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Volunteer volunteer : volunteers) {
                writer.write(volunteer.toCSV());
                writer.newLine();
            }
        }
    }

    /**
     * Carga la lista de Voluntarios desde un archivo CSV.
     *
     * @param filePath La ruta del archivo desde donde se cargarán los datos.
     * @return Una lista de objetos Volunteer cargados desde el archivo CSV.
     * @throws IOException Si ocurre un error al intentar leer el archivo.
     */
    public static List<Volunteer> loadFromCSV(String filePath) throws IOException {
        List<Volunteer> volunteers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                volunteers.add(Volunteer.fromCSV(line));
            }
        }
        return volunteers;
    }
/**
 * Genera un informe detallado de los voluntarios filtrados por una cantidad mínima de horas trabajadas.
 *
 * @param volunteers La lista de voluntarios a filtrar.
 * @param minHours La cantidad mínima de horas trabajadas para incluir en el informe.
 * @return Un string con el informe de los voluntarios que cumplen con el criterio.
 */
public static String generateVolunteerReport(List<Volunteer> volunteers, int minHours) {
 List<Volunteer> filteredVolunteers = filterVolunteersByHours(volunteers, minHours);
 StringBuilder report = new StringBuilder("Informe de Voluntarios con al menos " + minHours + " horas trabajadas:\n");
 
}
