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
 * Última modificación: 17/09/2024
 */

public class Adoption {
    // Atributos de la clase Adoption
    private Animal animal;             // El animal que ha sido adoptado
    private Volunteer adopter;         // El voluntario que gestionó la adopción
    private String adoptionDate;       // La fecha de adopción

    // Se encarga de inicializar los valores de los atributos
    public Adoption(Animal animal, Volunteer adopter, String adoptionDate) {
        this.animal = animal;                 // Inicializa el animal adoptado
        this.adopter = adopter;               // Inicializa el voluntario que gestionó la adopción
        this.adoptionDate = adoptionDate;     // Inicializa la fecha de adopción
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
    public Volunteer getAdopter() {
        return adopter;
    }

    /**
     * Obtiene la fecha de adopción.
     * 
     * @return La fecha en la que se realizó la adopción.
     */
    public String getAdoptionDate() {
        return adoptionDate;
    }

    /**
     * Retorna los detalles de la adopción en formato de String.
     * Incluye información sobre el animal, el voluntario y la fecha de adopción.
     * 
     * @return Los detalles de la adopción en un formato legible.
     */
    public String displayAdoptionDetails() {
        return "Detalles de la Adopción:\n" +
               "Animal adoptado: " + animal.getName() + " (ID: " + animal.getId() + ")\n" +
               "Raza: " + animal.getBreed() + "\n" +
               "Edad: " + animal.getAge() + " años\n" +
               "Descripción: " + animal.getDescription() + "\n" +
               "Gestionado por el voluntario: " + adopter.getName() + " (ID: " + adopter.getId() + ")\n" +
               "Fecha de adopción: " + adoptionDate;
    }
}
