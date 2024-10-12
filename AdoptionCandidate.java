/**
 * Universidad del Valle de Guatemala - Segundo Semestre 2024
 * Programación Orientada a Objetos (POO)
 * Proyecto Final
 * 
 * Clase AdoptionCandidate
 * 
 * La clase representa a un candidato para la adopción de un animal en un refugio.
 * Esta clase maneja la información sobre el adoptante, la experiencia con mascotas, el motivo de la adopción
 * y cualquier experiencia adicional requerida para animales peligrosos.
 * 
 * @author Angel Higueros
 * Fecha de creación: 18/09/2024 
 * Última modificación: 12/10/2024
 */

import java.util.ArrayList;
import java.util.List;

public class AdoptionCandidate {
    private List<Animal> animals;            // Lista por si un Adoptante, adopta más de un animal
    private Volunteer volunteer;             // Voluntario que esta en contacto con el adoptante
    private String reasonForAdoption,        // Motivo de la adopción  
                   name,                     // Nombre 
                   contactInfo;              // Información de Contacto
    private boolean hasPetExperience;        // Indicador de experiencia  
    private boolean additionalExperience;    // Indicador de experiencia  
    private int id;                          // ID del voluntario
    private static int idCounter = 1;        // ID único ascendiente  

    /**
     * Constructor que inicializa un candidato de adopción con la información del 
     * candidato y una lista de animales considerados para la adopción.
     *
     * @param additionalExperience Indica si el candidato tiene experiencia adicional en el cuidado de animales.
     * @param reasonForAdoption    La razón por la cual el candidato desea adoptar un animal.
     * @param name                 El nombre del candidato.
     * @param contactInfo          La información de contacto del candidato.
     * @param hasPetExperience     Indica si el candidato tiene experiencia previa con mascotas.
     */
    public AdoptionCandidate(boolean additionalExperience, String reasonForAdoption, String name, String contactInfo, boolean hasPetExperience) {
        this.additionalExperience = additionalExperience;
        this.reasonForAdoption = reasonForAdoption;
        this.name = name;
        this.contactInfo = contactInfo;
        this.hasPetExperience = hasPetExperience;
        this.id = idCounter++;              // Asignar ID único y aumentar el contador
        this.animals = new ArrayList<>(); 
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
     * Obtiene la información de contacto del voluntario.
     *
     * @return Lista de Animales que contiene los animales adoptados por el adoptante
     */
    public List<Animal> getAnimals() {
        return animals;
    }

    /**
     * Proporciona un resumen de los detalles de la adopción, incluyendo el nombre del animal,
     * el adoptante, la experiencia con mascotas y cualquier experiencia adicional si es necesario.
     *
     * @return Un String con los detalles resumidos del adoptante
    */ 
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Adoptante ID: ").append(id).append("\n")
        .append("Nombre: ").append(name).append("\n")
        .append("Información de Contacto: ").append(contactInfo).append("\n")
        .append("Motivo para la adopción: ").append(reasonForAdoption).append("\n")
        .append("Experiencia con mascotas: ").append(hasPetExperience ? "Si" : "No").append("\n")
        .append("Experiencia con Animales Peligrosos: ").append(additionalExperience ? "Si" : "No").append("\n");

        // Mostrar los animales adoptados por este adoptante, si los hay
        if (animals.isEmpty()) {
            sb.append("No ha adoptado ningún animal aún.\n");
        } else {
            sb.append("Animales adoptados:\n");
            for (Animal animal : animals) {
                sb.append("- ").append(animal.getName()).append(", Raza: ").append(animal.getBreed()).append(", Edad: ").append(animal.getAge()).append("\n");
            }
        }

        sb.append("Voluntario encargado: ").append(volunteer != null ? volunteer.getName() : "Ninguno").append("\n");
        return sb.toString();
    }

    /**
     * Obtiene el nombre del voluntario.
     *
     * @return Un String que representa el nombre del voluntario.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene la información de contacto del adoptante.
     *
     * @return String que contiene la información de contacto del adoptante.
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * Obtiene la información de si tiene experiencia con animales peligrosos.
     *
     * @return boolean que contiene la información si tiene experiencia con animales peligrosos.
     */
    public boolean getAdditionalExperience() {
        return additionalExperience;
    }    

    /**
     * Añade un animal a la lista del adoptante.
     */
    public void addAnimal(Animal animal) {
        if (animals == null) {
            animals = new ArrayList<>();  // Inicializar la lista si está vacía
        }
        animals.add(animal);  // Agregar el animal a la lista
    }

    /**
     * Añade el voluntario que gestiona el contacto con el adoptante.
     * @param volunteer El voluntario encargado del proceso de adopción.
     */
    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }
}
