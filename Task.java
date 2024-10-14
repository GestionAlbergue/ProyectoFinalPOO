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
 * Última modificación: 12/09/2024
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
     * @param taskName El nombre de la tarea.
     * @param description La descripción de la tarea.
     */
    public Task(String taskName, String description) {
        this.taskName = taskName;
        this.description = description;
        this.isCompleted = false;
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
     * Sobreescribe método toString y muestra los detalles de la tarea en formato CSV.
     * 
     * @return String con los detalles de la tarea
     */
    @Override
    public String toString() {
        return taskName + "," + description + "," + isCompleted;
    }
     /**
     * Guarda una lista de tareas en un archivo CSV.
     * 
     * @param tasks La lista de tareas a guardar.
     * @param fileName El nombre del archivo CSV donde se guardarán las tareas.
     * @throws IOException Si ocurre un error de entrada/salida durante la escritura del archivo.
     */
     public static void saveTasksToCSV(List<Task> tasks, String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Task task : tasks) {
                writer.println(task.toString());
            }
        }
    }
}
