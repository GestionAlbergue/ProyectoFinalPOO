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
 * Última modificación: 18/09/2024
 */

import java.util.Scanner;

public class AdoptionCandidate {
    private Animal animal;
    private Volunteer volunteer;
    private String additionalExperience, // Para animales peligrosos  
                   reasonForAdoption,    // Motivo de la adopción  
                   name,                 // Nombre 
                   contactInfo;          // Información de Contacto
    private boolean hasPetExperience;    // Indicador de experiencia  


    /**
     * Constructor que inicializa un candidato de adopción con un animal y un voluntario.
     *
     * @param animal    El animal que se está considerando para adopción.
     * @param volunteer El voluntario encargado del proceso de adopción.
     */
    public AdoptionCandidate(Animal animal, Volunteer volunteer) {
        this.animal = animal;
        this.volunteer = volunteer;
    }

    /**
     * Recolecta la información del adoptante, incluyendo la experiencia con mascotas,
     * el motivo de adopción y, si es necesario, experiencia con animales peligrosos.
     *
     * @param sc Un objeto utilizado para recibir la entrada del usuario.
     */
    public void collectAdoptionInfo(Scanner sc) {
        System.out.println("========== ADOPTANTE ==========");
        System.out.print("Nombre de Adoptante: ");
        this.name = sc.nextLine();

        System.out.print("Información de Contacto de Adoptante: ");
        this.contactInfo = sc.nextLine();

        System.out.print("¿Tienes experiencia con mascotas? (Si/No): ");
        String experienceResponse = sc.nextLine();
        this.hasPetExperience = experienceResponse.equalsIgnoreCase("Si");

        System.out.print("¿Cuál es tu razón para adoptar este animal?: ");
        this.reasonForAdoption = sc.nextLine();

        if (animal.getDangerLevel() > 0) {
            System.out.println("Este animal tiene un nivel de peligro: " + animal.getDangerLevelDescription());
            System.out.print("Ingrese su experiencia con animales peligrosos: ");
            additionalExperience = sc.nextLine();
        }

        //animal.setAdopted(true); // Marcar al animal como adoptado  
    }

    /**
     * Proporciona un resumen de los detalles de la adopción, incluyendo el nombre del animal,
     * el adoptante, la experiencia con mascotas y cualquier experiencia adicional si es necesario.
     *
     * @return Un String con los detalles resumidos de la adopción.
     */
    public String summarizeAdoptionDetails() {
        return "Detalles de adopción:\n"
                + "- Animal: " + animal.getName() + "\n"
                + "- Adoptante: " + volunteer.getName() + "\n"
                + "- Experiencia con mascotas: " + (hasPetExperience ? "Sí" : "No") + "\n"
                + "- Razón para adoptar: " + reasonForAdoption + "\n"
                + (animal.getDangerLevel() > 0 ? "- Experiencia adicional: " + additionalExperience + "\n" : "");
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
     * Obtiene la información de contacto del voluntario.
     *
     * @return String que contiene la información de contacto del voluntario.
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * Obtiene el animal asociado a este candidato de adopción.
     * 
     * @return El Animal que está siendo adoptado.
     */
    public Animal getAnimal() {
        return this.animal;
    }

}
