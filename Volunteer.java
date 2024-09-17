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
 * Última modificación: 12/09/2024
 */

public class Volunteer {
    // Atributos de la clase Volunteer
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
    public Volunteer(String name, String contactInfo) {
        this.id = idCounter++;              // Asignar ID único y aumentar el contador
        this.name = name;                   // Inicializa el nombre del voluntario
        this.contactInfo = contactInfo;     // Inicializa la información de contacto
        this.hoursWorked = 0;               // Inicializa las horas trabajadas en 0
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
}