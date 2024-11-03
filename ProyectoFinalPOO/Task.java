/**
 * Universidad del Valle 
 * Programación Orientada a Objetos 
 * 
 * Clase Task
 * 
 * La clase Task representa una tarea en el sistema de planificación del albergue.
 * Cada tarea tiene un nombre, una descripción y un estado de finalización.
 * Los voluntarios pueden seleccionar y completar estas tareas.
 * 
 * @author Marjori Flores
 * Fecha de creación: 15/09/2024
 * Última modificación: 13/09/2024
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private String taskName;       // Nombre de la tarea
    private String description;    // Descripcion de la tarea
    private boolean isCompleted;   // Completada o No
    private Volunteer volunteer;   // Voluntario encargado

    /**
     * Constructor para crear una nueva tarea.
     * 
     * @param taskName El nombre de la tarea
     * @param description La descripción de la tarea
     * @param isCompleted Identifica si la tarea esta completa o no
     * @param volunteer Elemento asociado con el voluntario
     */
    public Task(String taskName, String description, boolean isCompleted, Volunteer volunteer) {
        this.taskName = taskName;
        this.description = description;
        this.isCompleted = isCompleted;
        this.volunteer = volunteer;
    }

    /**
     * Obtiene el nombre de la tarea.
     * 
     * @return El nombre de la tarea.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Obtiene al voluntario encargado de la tarea
     * 
     * @return el Voluntario de la tarea
     */
    public Volunteer getVolunteer() {
        return this.volunteer;
    }

    /**
     * Obtiene la descripción de la tarea.
     * 
     * @return La descripción de la tarea.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Verifica si la tarea ha sido completada.
     * 
     * @return true si la tarea está completada, false de lo contrario.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Marca la tarea como completada.
     */
    public void completeTask() {
        this.isCompleted = true;
    }

    /**
     * Define el voluntario que realizó la tarea
     */
    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    /**
     * Sobreescribe método toString y muestra los detalles de la tarea.
     * 
     * @return String con los detalles de la tarea
     */
    @Override
    public String toString() {
        return "Tarea: " + this.taskName + "\n" +
               "Descripción: " + this.description + "\n" +
               "Voluntario Encargado: " + this.volunteer.getName() + " | ID: " + this.volunteer.getId() + "\n" +
               "Estado: " + (this.isCompleted ? "Completada" : "Pendiente");
    }

    /**
     * Método para convertir un objeto Task en una línea CSV.
     * @return La representación en formato CSV del objeto Task.
     */
    public String toCSV() {
        return taskName + "," + description + "," + isCompleted + "," + (volunteer != null ? volunteer.getId() : ""); // Guardar ID del voluntario
    }

    /**
     * Método para cargar un objeto Task desde una línea CSV.
     * @param csvLine La línea CSV que contiene la información del Task.
     * @param volunteers Lista de voluntarios disponibles para asociar con la tarea.
     * @return El objeto Task creado a partir de la línea CSV.
     */
    public static Task fromCSV(String csvLine, List<Volunteer> volunteers) {
        String[] fields = csvLine.split(",");
        String taskName = fields[0];
        String description = fields[1];
        boolean isCompleted = Boolean.parseBoolean(fields[2]);
        int volunteerId = fields.length > 3 ? Integer.parseInt(fields[3]) : -1; // ID del voluntario

        Volunteer volunteer = null;
        if (volunteerId != -1) {
            volunteer = volunteers.stream().filter(v -> v.getId() == volunteerId).findFirst().orElse(null); // Buscar el voluntario por ID
        }

        return new Task(taskName, description, isCompleted, volunteer);
    }

    /**
     * Método para guardar la lista de tareas a un archivo CSV.
     * @param tasks Lista de tareas a guardar en formato CSV.
     * @param filePath Ruta del archivo donde se guardará la información.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public static void saveToCSV(List<Task> tasks, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toCSV());
                writer.newLine();
            }
        }
    }

    /**
     * Método para cargar la lista de tareas desde un archivo CSV.
     * @param filePath Ruta del archivo CSV desde donde se cargarán las tareas.
     * @param volunteers Lista de voluntarios disponibles para asociar con las tareas cargadas.
     * @return Lista de tareas cargadas desde el archivo CSV.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public static List<Task> loadFromCSV(String filePath, List<Volunteer> volunteers) throws IOException {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Task.fromCSV(line, volunteers));
            }
        }
        return tasks;
    }

}
