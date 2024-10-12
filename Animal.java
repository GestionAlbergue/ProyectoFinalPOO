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
 * Última modificación: 12/10/2024
 */

public class Animal {
    private String name,                  // Nombre del animal
                   breed,                 // Raza del animal
                   description;           // Descripción del animal
    private int age,                      // Edad del animal
                id;                       // ID del animal
    private static int idCounter = 1;     // ID único ascendiente 
    private boolean adopted,              // Estado de adopción (true si está adoptado, false si no)
                    dangerLevel;          // Nivel de peligro (true si es peligroso, false si no)

    /**
     * Constructor de la clase Animal.
     * 
     * @param name        El nombre del animal.
     * @param breed       La raza del animal.
     * @param age         La edad del animal.
     * @param description Una breve descripción del animal.
     */
    public Animal(String name, String breed, int age, String description, boolean dangerLevel) {
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
    public boolean getDangerLevel() {
        return this.dangerLevel;
    }

    /**
     * Obtiene la descripación del Nivel de Peligro del Animal 
     * 
     * @return la descripción del Nivel de Peligro de, animal.
     */
    public String getDangerLevelDescription() {
        if(dangerLevel){
            return "Peligroso";
        } else if(!dangerLevel){
            return "No Peligroso";
        } else{
            return "Nivel de peligro desconocido";
        }
    }

    /**
     * Obtiene el nombre del animal.
     * 
     * @return El nombre del animal.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Obtiene la raza del animal.
     * 
     * @return La raza del animal.
     */
    public String getBreed() {
        return this.breed;
    }

    /**
     * Obtiene la edad del animal.
     * 
     * @return La edad del animal.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Obtiene la descripción del animal.
     * 
     * @return La descripción del animal.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Verifica si el animal ha sido adoptado.
     * 
     * @return true si el animal está adoptado, false en caso contrario.
     */
    public boolean isAdopted() {
        return this.adopted;
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
        return this.id;
    }

    /**
     * Sobreescribe el método toString para dar información detallada del animal 
     * 
     * @return String de detalles del animal.
     */
    @Override
    public String toString() {
        return "-------------------------------------------------------\n" +
            "ID: " + this.id + "\n" +
            "Nombre: " + this.name + "\n" +
            "Raza: " + this.breed + "\n" +
            "Edad: " + this.age + "\n" +
            "Descripción: " + this.description + "\n" +
            "Peligrosidad: " + this.getDangerLevelDescription() + "\n" +
            "-------------------------------------------------------\n";
    }
}
