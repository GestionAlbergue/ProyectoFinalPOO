/**
 * Universidad del Valle de Guatemala - Segundo Semestre 2024
 * Programación Orientada a Objetos (POO)
 * Proyecto Final
 * 
 * Clase Animal
 * 
 * La clase Animal representa a un animal en el sistema de gestión de albergue.
 * Cada animal tiene un nombre, raza, edad, descripción y un estado de adopción.
 * 
 * @author Daniela Navas
 * Fecha de creación: 12/09/2024 
 * Última modificación: 18/09/2024
 */

public class Animal {
    // Atributos de la clase Animal
    private String name,                  // Nombre del animal
                   breed,                 // Raza del animal
                   description;           // Descripción del animal
    private int age,                      // Edad del animal
                id,                       // ID del animal
                dangerLevel;              // Nivel de peligro
    private static int idCounter = 1;     // ID único ascendiente 
    private boolean adopted;              // Estado de adopción (true si está adoptado, false si no)

    /**
     * Constructor de la clase Animal.
     * 
     * @param name        El nombre del animal.
     * @param breed       La raza del animal.
     * @param age         La edad del animal.
     * @param description Una breve descripción del animal.
     */
    public Animal(String name, String breed, int age, String description, int dangerLevel) {
        this.id = idCounter++;        // Asignar ID único y aumentar el contador
        this.name = name;                 // Inicializa el nombre
        this.breed = breed;               // Inicializa la raza
        this.age = age;                   // Inicializa la edad
        this.description = description;   // Inicializa la descripción
        this.dangerLevel = dangerLevel;   // Inicializa el danger level
        this.adopted = false;             // Inicializa el estado de adopción como no adoptado
    }

    /**
     * Obtiene el Nivel de Peligro del Animal en número
     * 
     * @return el número de Peligro de, animal.
     */
    public int getDangerLevel() {
        return dangerLevel;
    }

    /**
     * Obtiene la descripación del Nivel de Peligro del Animal 
     * 
     * @return la descripción del Nivel de Peligro de, animal.
     */
    public String getDangerLevelDescription() {
        switch (dangerLevel) {
            case 0: return "No peligro";
            case 1: return "Levemente peligroso";
            case 2: return "Muy peligroso";
            default: return "Nivel de peligro desconocido";
        }
    }

    /**
     * Obtiene el nombre del animal.
     * 
     * @return El nombre del animal.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene la raza del animal.
     * 
     * @return La raza del animal.
     */
    public String getBreed() {
        return breed;
    }

    /**
     * Obtiene la edad del animal.
     * 
     * @return La edad del animal.
     */
    public int getAge() {
        return age;
    }

    /**
     * Obtiene la descripción del animal.
     * 
     * @return La descripción del animal.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Verifica si el animal ha sido adoptado.
     * 
     * @return true si el animal está adoptado, false en caso contrario.
     */
    public boolean isAdopted() {
        return adopted;
    }

    /**
     * Establece el estado de adopción del animal.
     * 
     * @param adopted true si el animal ha sido adoptado, false si no.
     */
    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    /**
     * Obtiene ID del animal.
     * 
     * @return ID del animal.
     */
    public int getId() {
        return id;
    }

    /**
     * Sobreescribe el método toString para dar indormación detallada del animal 
     * 
     * @return String de detalles del animal.
     */
    @Override
    public String toString() {
        return "-------------------------------------------------------\n" +
            "ID: " + id + "\n" +
            "Nombre: " + name + "\n" +
            "Raza: " + breed + "\n" +
            "Edad: " + age + "\n" +
            "Descripción: " + description + "\n" +
            "Peligroso: " + this.getDangerLevelDescription() + "\n" +
            "-------------------------------------------------------\n";
    }
}
