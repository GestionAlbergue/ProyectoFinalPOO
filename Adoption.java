/**
 * Universidad del Valle de Guatemala - Segundo Semestre 2024
 * Programación Orientada a Objetos (POO)
 * Proyecto Final
 * 
 * Clase Adoption
 * 
 * La clase Adoption representa el proceso de adopción de un
 * animal en el Albegue
 * 
 * @author Antony Barrios 
 * Fecha de creación: 17/09/2024 
 * Última modificación: 12/10/2024
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Adoption {
    private Animal animal;               // El animal que ha sido adoptado
    private Volunteer volunteer;         // El voluntario que gestionó la adopción
    private LocalDate adoptionDate;      // La fecha de adopción
    private AdoptionCandidate adopter;   // Persona que lo adopto 

    /**
     * Constructor que inicializa una adopción con un animal, un voluntario, 
     * la fecha de adopción y el candidato adoptante.
     *
     * @param animal        El animal que ha sido adoptado.
     * @param volunteer     El voluntario encargado de gestionar la adopción.
     * @param adoptionDate  La fecha en la que se realizó la adopción.
     * @param adopter       El candidato adoptante que adopta el animal.
     */
    public Adoption(Animal animal, Volunteer volunteer, LocalDate adoptionDate, AdoptionCandidate adopter) {
        this.animal = animal;                 // Inicializa el animal adoptado
        this.volunteer = volunteer;           // Inicializa el voluntario que gestionó la adopción
        this.adoptionDate = adoptionDate;     // Inicializa la fecha de adopción
        this.adopter = adopter;               // Inicializa al adoptante    
    }

    /**
     * Obtiene el animal adoptado.
     * 
     * @return El animal que ha sido adoptado.
     */
    public Animal getAnimal() {
        return animal;
    }

    /**
     * Obtiene el voluntario que gestionó la adopción.
     * 
     * @return El voluntario que gestionó la adopción.
     */
    public Volunteer getVolunteer() {
        return volunteer;
    }

    /**
     * Obtiene la fecha de adopción.
     * 
     * @return La fecha en la que se realizó la adopción.
     */
    public LocalDate getAdoptionDate() {
        return adoptionDate;
    }

    /**
     * Retorna los detalles de la adopción en formato de String.
     * Incluye información sobre el animal, el voluntario y la fecha de adopción.
     * 
     * @return Los detalles de la adopción en un formato legible.
     */
    public String displayAdoptionDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "-------------- ANIMAL -------------- \n" +
               "Animal adoptado: " + animal.getName() + " (ID: " + animal.getId() + ")\n" +
               "Raza: " + animal.getBreed() + "\n" +
               "Edad: " + animal.getAge() + " años\n" +
               "Descripción: " + animal.getDescription() + "\n" +
               "Gestionado por: " + volunteer.getName() + " (ID: " + volunteer.getId() + ")\n" +
               "Fecha de Adopción: " + adoptionDate.format(formatter) + "\n" +
               "------------- ADOPTANTE ------------- \n" +
               "Nombre: " + adopter.getName() + "\n" +
               "Informacion de Contacto: " + adopter.getContactInfo() + "\n";
    }
}
